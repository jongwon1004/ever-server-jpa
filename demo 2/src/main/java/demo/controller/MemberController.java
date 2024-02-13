package demo.controller;

import demo.exception.MemberSignupException;
import demo.request.SignupRequest;
import demo.service.MemberService;
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

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {

        try {
            memberService.validateUser(signupRequest);
            memberService.save(signupRequest);
        } catch (MemberSignupException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessages());
        }
        return ResponseEntity.ok("success");
    }
}
