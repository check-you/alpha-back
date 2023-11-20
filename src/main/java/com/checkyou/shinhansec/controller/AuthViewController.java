package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthViewController {
    private final MemberService memberService;
    @GetMapping("/api/user/confirm-email")
    public String confirmEmail(@RequestParam(required = false, value = "email") String email, @RequestParam(required = false, value = "authToken") String token){
        memberService.confirmEmail(email, token);
        return "completeAuth";
    }
}
