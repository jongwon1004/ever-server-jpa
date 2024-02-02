package demo.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class BoardTest {


    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Test
    public void boardTest() {
        LanguageType frontend = new LanguageType("FRONTEND");
        em.persist(frontend);

        Language html = new Language("HTML/CSS", frontend);
        em.persist(html);

        new Board(html, )
    }

}