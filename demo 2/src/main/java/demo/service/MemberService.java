package demo.service;

import demo.entity.Member;
import demo.entity.UserClass;
import demo.repository.MemberRepository;
import demo.repository.UserClassRepository;
import demo.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
