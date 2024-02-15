package ever.repository;

import ever.dto.MemberStatusDto;
import ever.entity.Member;
import ever.request.LoginRequest;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByNameStartingWith(String startName);

    Member loginRequestCheck(LoginRequest loginRequest);

    String findIdByLoginId(String loginId);

    MemberStatusDto userStatus(Long userId);

    Boolean signupDuplicateCheck(String field, String value);

}
