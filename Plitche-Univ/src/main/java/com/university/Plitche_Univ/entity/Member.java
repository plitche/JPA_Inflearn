package com.university.Plitche_Univ.entity;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;

    private String loginId;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    protected Member() {
    }

    public static Member from(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .loginId(memberRequestDto.getLoginId())
                .password(memberRequestDto.getPassword())
                .name(memberRequestDto.getName())
                .grade(memberRequestDto.getGrade())
                .address(memberRequestDto.getAddress())
        .build();
    }

    public static Member from(MemberRequestDto memberRequestDto, Grade grade, Address address) {
        return Member.builder()
                .loginId(memberRequestDto.getLoginId())
                .password(memberRequestDto.getPassword())
                .name(memberRequestDto.getName())
                .grade(grade)
                .address(address)
                .build();
    }

    public static Member createMember(String loginId, String password, String name, Grade grade, Address address) {
        Member member = new Member();
        member.loginId = loginId;
        member.password = password;
        member.name = name;
        member.grade = grade;
        member.address = address;
        return member;
    }
}
