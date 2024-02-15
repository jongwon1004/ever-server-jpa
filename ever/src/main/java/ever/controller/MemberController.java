package ever.controller;

import ever.exception.MemberSignupException;
import ever.request.SignupRequest;
import ever.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    /**
     * TODO: memberService.save (入力チェックが終わって最終的にDBに登録する時、EmailAuthの情報はDBから完全に削除される（使い捨て用DBのため）
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {

        try {
            memberService.validateUser(signupRequest);
            Long userId = memberService.save(signupRequest);
            System.out.println("userId = " + userId);
        } catch (MemberSignupException memberSignupException) {
            return ResponseEntity.badRequest().body(memberSignupException.getErrorMessages());
        }

        return ResponseEntity.ok("success");
    }
}
