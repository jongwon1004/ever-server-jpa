package demo.controller;

import demo.request.SignupRequest;
import demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        memberService.save(signupRequest);
        return "success";
    }
}
