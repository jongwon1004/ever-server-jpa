package ever.repository.member;

import ever.entity.Member;
import ever.repository.member.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);

}
