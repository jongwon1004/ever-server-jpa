package ever.service;

import ever.exception.MailSendException;
import ever.email.EmailContent;
import ever.entity.EmailAuth;
import ever.enums.StatusType;
import ever.repository.email.EmailAuthRepository;
import ever.request.EmailAuthCheckRequest;
import ever.request.EmailAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAuthService {

    private final JavaMailSender mailSender;
    private final EmailAuthRepository emailAuthRepository;

    /**
     * メール送信ロージック
     * @param emailAuthRequest Email値しか入ってない
     */
    public void sendEmailAuthCode(EmailAuthRequest emailAuthRequest) throws MailSendException {
        EmailAuth emailAuth = createCertNumber(emailAuthRequest); // DB登録
        sendEmailFinal(emailAuth.getEmail(), emailAuth.getCertificationNumber()); // メール送信
    }

    /**
     * DBに登録するメソッド
     */
    private EmailAuth createCertNumber(EmailAuthRequest emailAuthRequest) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusTenMin = now.plusMinutes(10);
        String certificationNumber = UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .replaceAll("_", "")
                .trim();

        EmailAuth emailAuth = EmailAuth.builder()
                .email(emailAuthRequest.getEmail())
                .regDate(now)
                .expireDate(nowPlusTenMin)
                .certificationNumber(certificationNumber)
                .statusType(StatusType.ACTIVE)
                .build();

        return emailAuthRepository.save(emailAuth);
    }

    private void sendEmailFinal(String email, String certificationNumber) throws MailSendException{
        EmailContent emailContent = EmailContent.createEmailContent(certificationNumber);

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailContent.getFrom());
            message.setTo(email);
            message.setSubject(emailContent.getSubject());
            message.setText(emailContent.getText());

            mailSender.send(message);
        } catch (MailException mailException) {
            log.error("メール送信失敗: " + mailException.getMessage());
            throw new MailSendException("メール送信に失敗しました。再度お試しください");
        }
    }

    public Map<String, String> certNumberCheck(EmailAuthCheckRequest emailAuthCheckRequest) {

        Map<String, String> response = null;

        String dbCertNumber = emailAuthRepository.findCertNumberByEmail(emailAuthCheckRequest.getEmail());

        // 認証番号が間違った時
        if(!emailAuthCheckRequest.getCertificationNumber().equals(dbCertNumber)) {
            response = new HashMap<>();
            response.put("certNumMismatch", "認証番号が間違っています");
        }

        // 民商番号が正しいとStatusをDELETEDに変更 -> 会員登録が終わった時DBから完全に削除される
        emailAuthRepository.updateEmailAuthStatus(emailAuthCheckRequest.getEmail());

        return response;
    }

    @Transactional
    public void removePreviousCertNumber(String email) {
        emailAuthRepository.deleteEmailAuthByEmail(email);
    }
}
