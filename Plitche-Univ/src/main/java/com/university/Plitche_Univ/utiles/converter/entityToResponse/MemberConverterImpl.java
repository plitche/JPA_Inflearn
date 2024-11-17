package com.university.Plitche_Univ.utiles.converter.entityToResponse;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;

public class MemberConverterImpl implements MemberConverter{

    public MemberResponseDto memberToResponseDto(Member member) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setLoginId(member.getLoginId());
        dto.setName(member.getName());
        dto.setGrade(member.getGrade());
        dto.setAddress(member.getAddress());
        return dto;
    }

}
