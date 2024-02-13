package demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.entity.Member;
import demo.request.LoginRequest;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static demo.entity.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberRepositoryImpl(EntityManager em, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.queryFactory = new JPAQueryFactory(em);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.loginId.eq(loginRequest.getUserId())
                )
                .fetchOne();

        if (findMember == null || bCryptPasswordEncoder.matches(loginRequest.getUserPass(), findMember.getPassword())) {
            return null;
        }

        return findMember;
    }
}
