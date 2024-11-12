package study.data_jpa.repository;

import study.data_jpa.Entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();
}
