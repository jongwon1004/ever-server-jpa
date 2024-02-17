package ever.repository;

import ever.dto.MemberStatusDto;
import ever.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberToDtoTest() {
        MemberStatusDto memberStatusDto = memberRepository.userStatus(1L);
        System.out.println("memberStatusDto = " + memberStatusDto);
    }

    @Test
    public void userStatusToDtoTest() {
        MemberStatusDto memberStatusDto = memberRepository.userStatus(4L);
        System.out.println("memberStatusDto = " + memberStatusDto);
    }

}