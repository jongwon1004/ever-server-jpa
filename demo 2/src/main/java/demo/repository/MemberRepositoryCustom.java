package demo.repository;

import demo.dto.MemberStatusDto;
import demo.entity.Member;
import demo.request.LoginRequest;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByNameStartingWith(String startName);

    Member loginRequestCheck(LoginRequest loginRequest);

    String findIdByLoginId(String loginId);

    MemberStatusDto userStatus(Long userId);

    Boolean signupDuplicateCheck(String field, String value);

}
