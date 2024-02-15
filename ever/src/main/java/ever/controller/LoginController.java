package ever.controller;

import ever.entity.Member;
import ever.repository.member.MemberRepository;
import ever.request.LoginRequest;
import ever.response.LoginResponse;
import ever.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@ModelAttribute LoginRequest loginRequest) {

        Member member = memberRepository.loginRequestCheck(loginRequest);
        if (member == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("loginFailed", false));
        } else {
            sessionManager.createSession(memberRepository.findIdByLoginId(loginRequest.getUserId()));
            return ResponseEntity.ok(new LoginResponse("loginSuccess", true));
        }

    }
}
