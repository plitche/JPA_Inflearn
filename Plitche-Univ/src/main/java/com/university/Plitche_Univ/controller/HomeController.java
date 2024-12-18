package com.university.Plitche_Univ.controller;

import ch.qos.logback.core.model.Model;
import com.university.Plitche_Univ.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model) {
        return "home/index";
    }

}
