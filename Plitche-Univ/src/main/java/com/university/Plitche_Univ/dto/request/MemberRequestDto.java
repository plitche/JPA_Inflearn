package com.university.Plitche_Univ.dto.request;

import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    String loginId;
    String password;
    String name;
    Grade grade;
    Address address;

}
