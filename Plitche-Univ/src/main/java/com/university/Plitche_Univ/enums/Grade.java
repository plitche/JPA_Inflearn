package com.university.Plitche_Univ.enums;

import lombok.Getter;

@Getter
public enum Grade {

    FRESHMAN("FRESHMAN", "1학년"),
    SOPHOMORE("SOPHOMORE", "2학년"),
    JUNIOR("JUNIOR", "3학년"),
    SENIOR("SENIOR", "4학년");

    private String value;
    private String description;

    Grade(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
