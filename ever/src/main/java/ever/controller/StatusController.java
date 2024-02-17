package ever.controller;

import ever.constants.LoginUserConst;
import ever.repository.member.MemberRepository;
import ever.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class StatusController {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    @GetMapping("/status")
    public ResponseEntity<?> status(@SessionAttribute(name = LoginUserConst.LOGIN_USER_NO, required = false) Long userId) {
        System.out.println("userId = " + userId);
        if (sessionManager.getSession(userId) == null) {
            return ResponseEntity.ok(Collections.singletonMap("result","No session found for user"));
        }
        return ResponseEntity.ok(memberRepository.userStatus(userId));
    }
}
