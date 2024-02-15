package ever.controller;

import ever.repository.MemberRepository;
import ever.session.SessionManager;
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
        if (sessionManager.getSession(userId) == null) {
            return ResponseEntity.ok("No session found for user");
        }
        return ResponseEntity.ok(memberRepository.userStatus(userId));
    }
}
