package com.university.Plitche_Univ.entity;

import com.university.Plitche_Univ.entity.embed.Address;
import com.university.Plitche_Univ.enums.Grade;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
