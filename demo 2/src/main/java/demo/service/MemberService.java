package demo.service;

import demo.entity.Member;
import demo.entity.UserClass;
import demo.exception.MemberSignupException;
import demo.repository.MemberRepository;
import demo.repository.UserClassRepository;
import demo.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final UserClassRepository userClassRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }

    /**
     * TODO: 현재 테스트로 userClassRepository.save 쪽 class 이름 새로 저장하니까 고쳐야됨
     */
    public Long save(SignupRequest signupRequest) {

        UserClass userClass = userClassRepository.findUserClassByClassName(signupRequest.getClassName())
                .orElseGet(() -> userClassRepository.save(new UserClass(signupRequest.getClassName())));

        Member member = memberRepository.save(Member.builder()
                .name(signupRequest.getName())
                .studentNumber(signupRequest.getStudentNumber())
                .loginId(signupRequest.getLoginId())
                .nickname(signupRequest.getNickname())
                .email(signupRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(signupRequest.getPassword()))
                .userClass(userClass)
                .interest(signupRequest.getInterest())
                .profileImage("GCP_IMAGE_LINK")
                .build());

        return member.getId();
    }

    public void validateUser(SignupRequest signupRequest) throws MemberSignupException {
        List<String> validationErrors = new ArrayList<>();

        if (!userClassRepository.existsByClassName(signupRequest.getClassName())) {
            validationErrors.add("有効なクラスではありません");
        }

        if (signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            validationErrors.add("確認用パスワードが一致してません");
        }

        if (duplicateCheck("loginId", signupRequest.getLoginId())) {
            validationErrors.add("ログインIDが重複しています");
        }

        if (duplicateCheck("studentNumber", signupRequest.getStudentNumber())) {
            validationErrors.add("学生番号が重複しています");
        }

        if (duplicateCheck("email", signupRequest.getEmail())) {
            validationErrors.add("メールアドレスが重複しています");
        }

        System.out.println("validationErrors = " + validationErrors);

        // 검사 결과 에러가 있을 경우, 사용자 정의 예외 발생
        if (!validationErrors.isEmpty()) {
            throw new MemberSignupException(validationErrors);
        }


    }

    private Boolean duplicateCheck(String field, String value) {
        return memberRepository.signupDuplicateCheck(field, value);
    }
}
