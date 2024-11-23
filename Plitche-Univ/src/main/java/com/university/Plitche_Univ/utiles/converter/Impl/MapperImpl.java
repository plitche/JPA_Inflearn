package com.university.Plitche_Univ.utiles.converter.Impl;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;
import com.university.Plitche_Univ.utiles.converter.Mapper;
import com.university.Plitche_Univ.utiles.converter.MemberConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {

    private final MemberConverter memberConverter;

    @Override
    public MemberResponseDto memberToResponseDto(Member member) {
        return memberConverter.memberToResponseDto(member);
    }
}
