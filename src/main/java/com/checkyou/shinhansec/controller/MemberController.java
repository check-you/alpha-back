package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.DTO.AccountDeleteRequestDTO;
import com.checkyou.shinhansec.DTO.AccountRequestDTO;
import com.checkyou.shinhansec.DTO.LogInRequestDTO;
import com.checkyou.shinhansec.DTO.SignInRequestDTO;
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
    @GetMapping("/confirm-email")
    public ResponseEntity confirmEmail(@RequestParam(required = false, value = "email") String email, @RequestParam(required = false, value = "authToken") String token){
        memberService.confirmEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accounts/new")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO request, Principal principal){

        memberService.createNewAccount(request, principal.getName());

        return ResponseEntity.ok("처리되었습니다.");
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountDeleteRequestDTO request ){

        memberService.deleteAccount(request);

        return ResponseEntity.ok("처리되었습니다.");
    }
}
