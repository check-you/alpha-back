package com.kids.alpha.shinhansec.controller;

import com.kids.alpha.shinhansec.DTO.AccountDeleteRequestDTO;
import com.kids.alpha.shinhansec.DTO.LogInRequestDTO;
import com.kids.alpha.shinhansec.DTO.AccountRequestDTO;
import com.kids.alpha.shinhansec.DTO.SignInRequestDTo;
import com.kids.alpha.shinhansec.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
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

    @PostMapping("/accounts/new")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO request, Principal principal){

        memberService.createNewAccount(request, principal.getName());

        return ResponseEntity.ok("처리되었습니다.");
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountDeleteRequestDTO request, Principal principal){

        memberService.deleteAccount(request, principal.getName());

        return ResponseEntity.ok("처리되었습니다.");
    }
}
