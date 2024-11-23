package com.university.Plitche_Univ.controller;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponseDto> getMembers(
            PageRequest pageRequest
    ) {
        return memberService.getMemberList(pageRequest);
    }

    @GetMapping("/user-id/{id}")
    public MemberResponseDto getFindMemberById(
            @PathVariable("id") String userId
    ) {
        return memberService.getMemberById(userId);
    }

    @PostMapping
    public ResponseEntity<String> createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        memberService.saveMember(memberRequestDto);
        return ResponseEntity.ok("success");
    }
}
