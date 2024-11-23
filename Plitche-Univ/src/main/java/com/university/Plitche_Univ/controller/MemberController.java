package com.university.Plitche_Univ.controller;

import com.university.Plitche_Univ.dto.request.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberRequestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        return "member/login";
    }

}
