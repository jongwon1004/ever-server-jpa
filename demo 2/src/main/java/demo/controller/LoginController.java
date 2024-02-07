package demo.controller;

import demo.entity.Member;
import demo.repository.MemberRepository;
import demo.request.LoginRequest;
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
    public ResponseEntity<String> login(@ModelAttribute LoginRequest loginRequest) {
        log.info("요청 들어옴");

        Member member = memberRepository.loginRequestCheck(loginRequest);
        if (member == null) {
            return ResponseEntity.badRequest().body("아이디 또는 비밀번호가 맞지않습니다");
        } else return ResponseEntity.ok("로그인에 성공하였습니다");

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
