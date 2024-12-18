package com.university.Plitche_Univ.dto.response;

import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private String loginId;
    private String name;
    private Grade grade;
    private Address address;

}
