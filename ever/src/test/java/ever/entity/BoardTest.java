package ever.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.repository.member.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static ever.entity.QBoard.*;
import static ever.entity.QMember.*;


@SpringBootTest
@Transactional
@Commit
class BoardTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void boardTest() {
        Member findMember = queryFactory
                .selectFrom(member)
                .orderBy(member.id.asc())
                .fetchFirst();
        LanguageType frontend = new LanguageType("FRONTEND");
        em.persist(frontend);

        Language html = new Language("HTML/CSS", frontend);
        em.persist(html);

        Board regBoard = new Board(html, findMember, "hello world", "this is content");
        em.persist(regBoard);

        em.flush();
        em.clear();

        Board findBoard = queryFactory
                .selectFrom(board)
                .join(board.member).fetchJoin()
                .join(board.language).fetchJoin()
                .fetchOne();
        System.out.println("findBoard = " + findBoard);

    }

    @Test
    public void test() {
        Optional<Member> findMember = memberRepository.findById(2L);
        System.out.println("findMember.orElseThrow(() -> new IllegalStateException(\"Not Member\"))\n                .getName() = " + findMember.orElseThrow(() -> new IllegalStateException("Not Member"))
                .getName());
    }




}