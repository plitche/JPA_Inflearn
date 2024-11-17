package com.university.Plitche_Univ.service.impl;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.Member;
import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import com.university.Plitche_Univ.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    public void memberSaveTest() {
        //given
        Address address = new Address("Korean", "Seoul", "123-4567");
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setLoginId("plitche");
        memberRequestDto.setPassword("1234");
        memberRequestDto.setName("Yongsoo Kwon");
        memberRequestDto.setGrade(Grade.FRESHMAN);
        memberRequestDto.setAddress(address);

        //when
        memberService.saveMember(memberRequestDto);

        //then
        MemberResponseDto findMember = memberService.getMemberById("plitche");
        Assertions.assertThat(findMember).isNotNull();
        Assertions.assertThat(findMember.getLoginId()).isEqualTo(memberRequestDto.getLoginId());
    }

}