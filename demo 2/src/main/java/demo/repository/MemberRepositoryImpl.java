package demo.repository;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.dto.MemberStatusDto;
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

        System.out.println("findMember = " + findMember);

        if (findMember == null || !bCryptPasswordEncoder.matches(loginRequest.getUserPass(), findMember.getPassword())) {
            return null;
        }

        return findMember;
    }

    @Override
    public String findIdByLoginId(String loginId) {

        return String.valueOf(
                queryFactory
                        .selectFrom(member)
                        .where(member.loginId.eq(loginId))
                        .fetchOne()
                        .getId());
    }

    @Override
    public MemberStatusDto userStatus(Long userId) {
        return
                queryFactory
                        .select(
                                Projections.fields(MemberStatusDto.class,
                                        member.id,
                                        member.name,
                                        member.studentNumber,
                                        member.loginId,
                                        member.nickname,
                                        member.email,
                                        member.userClass.className,
                                        member.interest,
                                        member.profileImage,
                                        member.introduction)
                        ).from(member)
                        .where(member.id.eq(userId))
                        .fetchOne();
    }

    @Override
    public Boolean signupDuplicateCheck(String field, String value) {

        BooleanExpression predicate = null;

        switch (field) {
            case "loginId":
                predicate = member.loginId.eq(value);
                break;
            case "studentNumber":
                predicate = member.studentNumber.eq(value);
                break;
            case "nickname":
                predicate = member.nickname.eq(value);
                break;
            case "email":
                predicate = member.email.eq(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }

        return queryFactory
                .selectFrom(member)
                .where(predicate) // 해당 필드의 값이 존재 ?
                .fetchFirst() != null;  // 존재하지 않으면 false
    }

}
