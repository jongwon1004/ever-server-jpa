package demo.service;

import demo.entity.Member;
import demo.entity.UserClass;
import demo.exception.MemberSignupException;
import demo.repository.MemberRepository;
import demo.repository.UserClassRepository;
import demo.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final UserClassRepository userClassRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }

    public Long save(SignupRequest signupRequest) {

        UserClass userClass = userClassRepository.findUserClassByClassName(signupRequest.getClassName())
                .get(); // memberService.validateUser でクラス名の入力チェックをしたので、ここではただget()で持ってくるだけ

        Member member = memberRepository.save(Member.builder()
                .name(signupRequest.getName())
                .studentNumber(signupRequest.getStudentNumber())
                .loginId(signupRequest.getLoginId())
                .nickname(signupRequest.getNickname())
                .email(signupRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(signupRequest.getPassword()))
                .userClass(userClass)
                .interest(signupRequest.getInterest())
                .profileImage("https://storage.googleapis.com/" + bucketName + "/animal" + (new Random().nextInt(9) + 1) + ".png")
                .build());

        return member.getId();
    }

    public void validateUser(SignupRequest signupRequest) throws MemberSignupException {
        log.info("SignupRequest ={}", signupRequest);

        Map<String, String> validationErrors = new HashMap<>();

        if (!userClassRepository.existsByClassName(signupRequest.getClassName())) {
            validationErrors.put("errorClassMessage", "有効なクラスではありません");
        }

        if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            validationErrors.put("passwordMismatch", "確認用パスワードが一致してません");
        }

        if (duplicateCheck("loginId", signupRequest.getLoginId())) {
            validationErrors.put("errorIdMessage","ログインIDが重複しています");
        }

        if (duplicateCheck("studentNumber", signupRequest.getStudentNumber())) {
            validationErrors.put("errorStudentNumberMessage","学生番号が重複しています");
        }

        if (duplicateCheck("email", signupRequest.getEmail())) {
            validationErrors.put("errorEmailMessage","メールアドレスが重複しています");
        }

        if (duplicateCheck("nickname", signupRequest.getNickname())) {
            validationErrors.put("errorNicknameMessage", "ニックネームが重複しています");
        }

        // 검사 결과 에러가 있을 경우, 사용자 정의 예외 발생
        if (!validationErrors.isEmpty()) {
            throw new MemberSignupException(validationErrors);
        }


    }

    private Boolean duplicateCheck(String field, String value) {
        return memberRepository.signupDuplicateCheck(field, value);
    }
}
