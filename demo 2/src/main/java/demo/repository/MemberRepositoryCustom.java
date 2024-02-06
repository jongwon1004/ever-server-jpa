package demo.repository;

import demo.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByNameStartingWith(String startName);
}
