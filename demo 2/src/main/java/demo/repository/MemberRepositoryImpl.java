package demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.entity.Member;
import demo.request.LoginRequest;
import jakarta.persistence.EntityManager;
import java.util.List;

import static demo.entity.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Member> findByNameStartingWith(String startName) {
        return queryFactory
                .selectFrom(member)
                .where(member.name.startsWith(startName))
                .fetch();
    }

    @Override
    public Member loginRequestCheck(LoginRequest loginRequest) {

        return queryFactory
                .selectFrom(member)
                .where(
                        member.loginId.eq(loginRequest.getUserId()),
                        member.password.eq(loginRequest.getUserPass())
                )
                .fetchOne();
    }
}
