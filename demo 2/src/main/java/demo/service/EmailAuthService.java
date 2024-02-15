package demo.service;

import demo.exception.MailSendException;
import demo.email.EmailContent;
import demo.entity.EmailAuth;
import demo.enums.StatusType;
import demo.repository.EmailAuthRepository;
import demo.request.EmailAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void sendEmailAuthCode(EmailAuthRequest emailAuthRequest) throws MailSendException{
        EmailAuth emailAuth = createCertificationNumber(emailAuthRequest); // DB登録
        sendEmailFinal(emailAuth.getEmail(), emailAuth.getCertificationNumber()); // メール送信
    }

    /**
     * DBに登録するメソッド
     */
    private EmailAuth createCertificationNumber(EmailAuthRequest emailAuthRequest) {
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

}
