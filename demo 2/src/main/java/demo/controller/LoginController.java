package demo.controller;

import demo.entity.Member;
import demo.repository.MemberRepository;
import demo.request.LoginRequest;
import demo.response.LoginResponse;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        log.info("요청 들어옴");
        log.info("LoginRequest ={}", loginRequest);

        Member member = memberRepository.loginRequestCheck(loginRequest);
        if (member == null) {
            session.setAttribute("LOGINID", loginRequest.getUserId());
            return ResponseEntity.badRequest().body(new LoginResponse("loginFailed", false));
        } else return ResponseEntity.ok(new LoginResponse("loginSuccess", true));

    }

    @GetMapping
    public ResponseEntity<String> sessionCheck(HttpSession session) {
        return ResponseEntity.ok((String) session.getAttribute("LOGINID"));
    }
}
