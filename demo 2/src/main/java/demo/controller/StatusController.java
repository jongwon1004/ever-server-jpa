package demo.controller;

import demo.repository.MemberRepository;
import demo.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class StatusController {

    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok(memberRepository.userStatus(userId));
    }
}
