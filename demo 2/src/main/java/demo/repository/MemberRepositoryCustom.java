package demo.repository;

import demo.entity.Member;
import demo.request.LoginRequest;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByNameStartingWith(String startName);

    Member loginRequestCheck(LoginRequest loginRequest);

}
