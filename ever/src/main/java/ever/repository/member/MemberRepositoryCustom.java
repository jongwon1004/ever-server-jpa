package ever.repository.member;

import ever.dto.MemberStatusDto;
import ever.entity.Member;
import ever.request.LoginRequest;

import java.util.List;

public interface MemberRepositoryCustom {

    Member loginRequestCheck(LoginRequest loginRequest);

    Long findIdByLoginId(String loginId);

    MemberStatusDto userStatus(Long userId);

    Boolean signupDuplicateCheck(String field, String value);

}
