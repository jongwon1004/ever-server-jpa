package ever.repository.email;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.entity.EmailAuth;
import ever.entity.QEmailAuth;
import jakarta.persistence.EntityManager;

import static ever.entity.QEmailAuth.emailAuth;

public class EmailAuthRepositoryImpl implements EmailAuthRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public EmailAuthRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public String findCertNumberByEmail(String email) {
        return queryFactory
                .select(emailAuth.certificationNumber)
                .from(emailAuth)
                .where(emailAuth.email.eq(email))
                .fetchOne();
    }
}
