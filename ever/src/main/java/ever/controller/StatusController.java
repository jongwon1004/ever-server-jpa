package ever.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ever.constants.LoginUserConst;
import ever.dto.MemberStatusDto;
import ever.repository.member.MemberRepository;
import ever.session.SessionManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @Getter
    @AllArgsConstructor
    static class StatusResult<T, U, V> {
        @JsonProperty("isLoggedIn")
        private boolean isLoggedIn;
        private T userInfo;
        private U notificationInfo;
        private V bookmarkInfo;
    }

    @GetMapping("/status")
    public ResponseEntity<StatusResult<Map, Map, Map>> status(@SessionAttribute(name = LoginUserConst.LOGIN_USER_NO, required = false) Long userId) {
        System.out.println("userId = " + userId);
        if (sessionManager.getSessionUserId() == null) {
            return ResponseEntity.ok(new StatusResult<>(
                    false,
                    Collections.singletonMap("result", "No session found for user"),
                    Collections.emptyMap(),
                    Collections.emptyMap()));
        }

        MemberStatusDto memberStatusDto = memberRepository.userStatus(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> loginUserInfoMap = objectMapper.convertValue(memberStatusDto, HashMap.class);
        Map<String, Object> notificationMap = Collections.emptyMap();
        Map<String, Object> bookmarkMap = Collections.emptyMap();

        return ResponseEntity.ok(new StatusResult<>(true, loginUserInfoMap, notificationMap, bookmarkMap));
    }
}
