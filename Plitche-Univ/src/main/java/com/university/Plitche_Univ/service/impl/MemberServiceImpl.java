package com.university.Plitche_Univ.service.impl;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;
import com.university.Plitche_Univ.repository.MemberRepo;
import com.university.Plitche_Univ.service.MemberService;
import com.university.Plitche_Univ.utiles.converter.MemberConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    private final MemberConverter memberConverter;

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMemberList(PageRequest pageRequest) {
        List<Member> findMembers = memberRepo.findAll();
        return findMembers.stream().map(
                memberConverter::memberToResponseDto
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberById(String userId) {
        Member findMember = memberRepo.findMemberByLoginId(userId);
        return memberConverter.memberToResponseDto(findMember);
    }

    @Override
    public void saveMember(MemberRequestDto memberRequestDto) {
        Member member = Member.from(memberRequestDto);
        memberRepo.save(member);
    }
}
