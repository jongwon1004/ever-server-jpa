package demo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.dto.MemberStatusDto;
import demo.dto.QMemberStatusDto;
import demo.entity.Member;
import demo.entity.UserClass;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static demo.entity.QMember.*;

@SpringBootTest
@Commit
@Transactional
public class MemberInitTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void initMember() {
        UserClass sk2a = new UserClass("SK2A");
        UserClass sk1a = new UserClass("SK1A");
        UserClass sk3a = new UserClass("SK3A");
        UserClass sk2b = new UserClass("SK2B");

        em.persist(sk2a);
        em.persist(sk3a);
        em.persist(sk1a);
        em.persist(sk2b);

        Member member1 = new Member("CHOIJONGWON1", "2220019", "whddnjs33401", "johnny1", "whddnjs33401@naver.com", "dnflwlq1408", sk2a);
        Member member2 = new Member("CHOIJONGWON2", "2220020", "whddnjs33402", "johnny2", "whddnjs33402@naver.com", "dnflwlq1408", sk1a);
        Member member3 = new Member("CHOIJONGWON3", "2220021", "whddnjs33403", "johnny3", "whddnjs33403@naver.com", "dnflwlq1408", sk3a);
        Member member4 = new Member("CHOIJONGWON4", "2220022", "whddnjs33404", "johnny4", "whddnjs33404@naver.com", "dnflwlq1408", sk2b);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void initCheck() {
        List<Member> members = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member1 : members) {
            System.out.println("member1 = " + member1);
        }
    }

    @Test
    public void selectMemberByDto1() {

        List<MemberStatusDto> result = queryFactory // @QueryProjection 을 안쓴 경우
                .select(
                        Projections.fields(
                                MemberStatusDto.class,
                                member.id,
                                member.name,
                                member.email
                        )
                ).from(member)
                .fetch();


        for (MemberStatusDto memberStatusDto : result) {
            System.out.println("memberStatusDto = " + memberStatusDto);
        }
    }

    @Test
    public void selectMemberByDto2() {

        List<MemberStatusDto> result = queryFactory // @QueryProjection 을 사용한 경우
                .select(
                        new QMemberStatusDto(member.id, member.name)
                ).from(member)
                .fetch();

        for (MemberStatusDto memberStatusDto : result) {
            System.out.println("memberStatusDto = " + memberStatusDto);
        }
    }

}
