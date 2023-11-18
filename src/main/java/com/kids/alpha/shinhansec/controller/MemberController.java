package com.kids.alpha.shinhansec.controller;

import com.kids.alpha.shinhansec.DTO.LogInRequestDTO;
import com.kids.alpha.shinhansec.DTO.SignInRequestDTo;
import com.kids.alpha.shinhansec.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LogInRequestDTO userLoginRequestDto) throws Exception {
        return memberService.login(userLoginRequestDto.getAccountNumber(), userLoginRequestDto.getPassword());
    }
    @PostMapping("/signIn")
    public ResponseEntity signin(@RequestBody SignInRequestDTo userSignInRequestDto) throws Exception {
        return memberService.signin(userSignInRequestDto.getAccountNumber(), userSignInRequestDto.getPassword());
    }
}
