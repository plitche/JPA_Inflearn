package com.university.Plitche_Univ.service.impl;

import com.university.Plitche_Univ.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
