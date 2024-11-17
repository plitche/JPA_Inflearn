package com.university.Plitche_Univ.utiles.converter.entityToResponse;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;

public interface MemberConverter {

    public MemberResponseDto memberToResponseDto(Member member);

}
