package com.university.Plitche_Univ.service;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MemberService {

    List<MemberResponseDto> getMemberList(PageRequest pageRequest);

    MemberResponseDto getMemberById(String userId);

    void saveMember(MemberRequestDto memberRequestDto);
}
