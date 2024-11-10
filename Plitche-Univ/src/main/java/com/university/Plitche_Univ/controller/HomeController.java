package com.university.Plitche_Univ.controller;

import com.university.Plitche_Univ.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "Hello world";
    }

    @GetMapping("/test")
    public int test() {
        return memberService.sum(1, 2);
    }
}
