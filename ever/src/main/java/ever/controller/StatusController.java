package ever.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ever.constants.LoginUserConst;
import ever.dto.MemberStatusDto;
import ever.repository.member.MemberRepository;
import ever.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


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
        if (sessionManager.getSessionUserId() == null) {
            return ResponseEntity.ok(Collections.singletonMap("result","No session found for user"));
        }
        MemberStatusDto memberStatusDto = memberRepository.userStatus(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(memberStatusDto, Map.class);

        return ResponseEntity.ok(map);
    }
}
