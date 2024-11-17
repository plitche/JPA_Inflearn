package com.university.Plitche_Univ.service;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {

    List<MemberResponseDto> getMemberList();

    MemberResponseDto getMemberById(String userId);
}
