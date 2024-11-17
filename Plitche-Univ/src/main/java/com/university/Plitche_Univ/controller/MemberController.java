package com.university.Plitche_Univ.controller;

import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponseDto> getMembers(
            Pageable pageable
    ) {
        return memberService.getMemberList();
    }

    @GetMapping("/user-id/{UID}")
    public MemberResponseDto getFindMember(
            @RequestParam("UID") String userId
    ) {
        return memberService.getMemberById(userId);
    }

}
