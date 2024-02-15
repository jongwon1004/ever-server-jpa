package ever.repository.email;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.entity.EmailAuth;
import ever.entity.QEmailAuth;
import ever.enums.StatusType;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public EmailAuth isEmailAuthenticated(String email) {
        return queryFactory
                .selectFrom(emailAuth)
                .where(emailAuth.email.eq(email))
                .fetchOne();
    }

    /**
     * updateだけなので、em.clear,flushはしない
     */
    @Override
    @Transactional
    public void updateEmailAuthStatus(String email) {
        queryFactory
                .update(emailAuth)
                .set(emailAuth.statusType, StatusType.DELETED)
                .where(emailAuth.email.eq(email))
                .execute();
    }
}
