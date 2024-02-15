package demo.controller;

import demo.request.EmailAuthRequest;
import demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmailAuthController {

    private final MemberService memberService;

    /**
     * @param emailAuthRequest　このコントローラーメソッドが呼ばれた時点ではemailの値しか入ってない
     */
    @PostMapping("/emailAuthRequest")
    public ResponseEntity<?> emailAuthRequest(@RequestBody EmailAuthRequest emailAuthRequest) {
        log.info("EmailAuthRequest={}", emailAuthRequest);
        if (memberService.duplicateCheckPub("email", emailAuthRequest.getEmail())) { // 重複==true

        }

        return ResponseEntity.ok().build();
    }
}
