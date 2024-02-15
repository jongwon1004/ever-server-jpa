package ever.controller;

import ever.exception.MailSendException;
import ever.request.EmailAuthRequest;
import ever.service.EmailAuthService;
import ever.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmailAuthController {

    private final MemberService memberService;
    private final EmailAuthService emailAuthService;

    /**
     * @param emailAuthRequest 　このコントローラーメソッドが呼ばれた時点ではemailの値しか入ってない
     */
    @PostMapping("/emailAuthRequest")
    public ResponseEntity<Object> emailAuthRequest(@RequestBody EmailAuthRequest emailAuthRequest) {
        log.info("EmailAuthRequest={}", emailAuthRequest);
        if (memberService.duplicateCheckPub("email", emailAuthRequest.getEmail())) { // 重複==true
            return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
                    .body(Collections.singletonMap("errorEmailMessage", "メールアドレスが重複しています"));
        }

        // メールアドレスが重複してない場合はメール送信ロージック
        try {
            emailAuthService.sendEmailAuthCode(emailAuthRequest);
        } catch (MailSendException mailSendException) {
            return ResponseEntity.status(421).body(mailSendException.getErrorMessage());
        }

        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("/mailAuthCheck")
//    public ResponseEntity mail
}
