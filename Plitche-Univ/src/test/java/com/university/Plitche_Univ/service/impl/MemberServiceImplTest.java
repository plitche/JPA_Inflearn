package com.university.Plitche_Univ.service.impl;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.dto.response.MemberResponseDto;
import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import com.university.Plitche_Univ.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired MemberService memberService;

    @BeforeEach
    void initData() {
        for (int i = 0; i < 10; i++) {
            Address address = new Address("Korea_" + i, "Seoul_" + i, i + "23-4567");
            MemberRequestDto memberRequestDto = new MemberRequestDto();
            memberRequestDto.setLoginId("plitche_" + i);
            memberRequestDto.setPassword("1234");
            memberRequestDto.setName("Yongsoo Kwon");

            switch (i) {
                case 0:
                case 1:
                case 2: memberRequestDto.setGrade(Grade.FRESHMAN); break;
                case 3:
                case 4:
                case 5: memberRequestDto.setGrade(Grade.SOPHOMORE); break;
                case 6:
                case 7:
                case 8: memberRequestDto.setGrade(Grade.JUNIOR); break;
                default: memberRequestDto.setGrade(Grade.SENIOR); break;
            }

            memberRequestDto.setAddress(address);
        }
    }

    @AfterEach
    void clearData() {

    }

    @Test
    public void memberSaveTest() {
        //given
        Address address = new Address("Korea", "Seoul", "123-4567");
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