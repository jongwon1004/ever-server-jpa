package demo.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static demo.entity.QBoard.*;
import static demo.entity.QMember.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@Commit
class BoardTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

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

}