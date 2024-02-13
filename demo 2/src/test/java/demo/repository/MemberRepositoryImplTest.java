package demo.repository;

import demo.dto.MemberStatusDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

}