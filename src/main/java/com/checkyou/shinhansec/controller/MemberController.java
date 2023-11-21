package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.DTO.*;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.jwt.TokenInfo;
import com.checkyou.shinhansec.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody LogInRequestDTO userLoginRequestDto) throws Exception {
        return memberService.login(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
    }
    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody SignInRequestDTO userSignInRequestDto) throws Exception {
        return memberService.signIn(userSignInRequestDto.getEmail(), userSignInRequestDto.getName(), userSignInRequestDto.getPhoneNumber(), userSignInRequestDto.getPassword());
    }
    @PostMapping("/confirm/auth")
    public ApiResponse<Boolean> confirmAuth(@RequestBody ConfirmRequestDTO confirmRequestDTO) throws Exception {
        return memberService.checkEmail(confirmRequestDTO.getEmail());
    }

    @GetMapping("/duplicate/{email}")
    public ApiResponse<Boolean> checkDuplicateEmail(@PathVariable("email") String email){
        return ApiResponse.success(memberService.checkDuplicateEmail(email));
    }

}
