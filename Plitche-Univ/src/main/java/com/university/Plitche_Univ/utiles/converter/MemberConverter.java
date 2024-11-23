package com.university.Plitche_Univ.utiles.converter;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;

public interface MemberConverter {

    MemberResponseDto memberToResponseDto(Member member);

}
