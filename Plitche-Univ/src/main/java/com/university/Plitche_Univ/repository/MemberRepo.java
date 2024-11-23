package com.university.Plitche_Univ.repository;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {

//    List<Member> findAll(Pageable pageable);
    List<Member> findAll();

    Member findMemberByLoginId(String loginId);


}
