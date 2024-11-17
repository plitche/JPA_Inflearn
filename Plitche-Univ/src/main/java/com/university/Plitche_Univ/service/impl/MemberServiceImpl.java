package com.university.Plitche_Univ.service.impl;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;
import com.university.Plitche_Univ.repository.MemberRepo;
import com.university.Plitche_Univ.service.MemberService;
import com.university.Plitche_Univ.utiles.converter.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    private final Mapper mapper;

    @Override
    public List<MemberResponseDto> getMemberList() {
        List<Member> findMembers = memberRepo.findMemberAll();
        return findMembers.stream().map(
                mapper::memberToResponseDto
        ).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto getMemberById(String userId) {
        Member findMember = memberRepo.findMemberByLoginId(userId);
        return mapper.memberToResponseDto(findMember);
    }
}
