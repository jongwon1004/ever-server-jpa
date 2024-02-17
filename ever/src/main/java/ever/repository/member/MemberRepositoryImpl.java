package ever.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.dto.MemberStatusDto;
import ever.entity.Member;
import ever.request.LoginRequest;
import ever.entity.QMember;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
                .selectFrom(QMember.member)
                .where(QMember.member.name.startsWith(startName))
                .fetch();
    }

    @Override
    public Member loginRequestCheck(LoginRequest loginRequest) {

        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(
                        QMember.member.loginId.eq(loginRequest.getUserId())
                )
                .fetchOne();

        System.out.println("findMember = " + findMember);

        if (findMember == null || !bCryptPasswordEncoder.matches(loginRequest.getUserPass(), findMember.getPassword())) {
            return null;
        }

        return findMember;
    }

    @Override
    public Long findIdByLoginId(String loginId) {

        return queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.loginId.eq(loginId))
                .fetchOne()
                .getId();
    }

    @Override
    public MemberStatusDto userStatus(Long userId) {
        System.out.println("여기들어옴 ?");
        return
                queryFactory
                        .select(
                                Projections.fields(MemberStatusDto.class,
                                        QMember.member.id,
                                        QMember.member.name,
                                        QMember.member.studentNumber,
                                        QMember.member.loginId,
                                        QMember.member.nickname,
                                        QMember.member.email,
                                        QMember.member.userClass.className,
                                        QMember.member.interest,
                                        QMember.member.profileImage,
                                        QMember.member.introduction)
                        ).from(QMember.member)
                        .where(QMember.member.id.eq(userId))
                        .fetchOne();
    }

    @Override
    public Boolean signupDuplicateCheck(String field, String value) {

        BooleanExpression predicate = null;

        switch (field) {
            case "loginId":
                predicate = QMember.member.loginId.eq(value);
                break;
            case "studentNumber":
                predicate = QMember.member.studentNumber.eq(value);
                break;
            case "nickname":
                predicate = QMember.member.nickname.eq(value);
                break;
            case "email":
                predicate = QMember.member.email.eq(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }

        return queryFactory
                .selectFrom(QMember.member)
                .where(predicate) // 해당 필드의 값이 존재 ?
                .fetchFirst() != null;  // 존재하지 않으면 false
    }





}
