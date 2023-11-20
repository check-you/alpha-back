package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.DTO.*;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.service.MemberService;
import com.checkyou.shinhansec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final MemberService memberService;

    @GetMapping("/{email}")
    public ApiResponse<AccountResponseDTO> getAccounts(@PathVariable("email") String email) throws Exception {
        return memberService.getAccounts(email);
    }
    @PostMapping("")
    public ApiResponse<String> createAccount(@RequestBody AccountRequestDTO request, Principal principal) throws Exception {
        return memberService.createNewAccount(request, principal.getName());
    }

    @DeleteMapping("")
    public ApiResponse<String> deleteAccount(@RequestBody AccountDeleteRequestDTO request) throws Exception {
        memberService.deleteAccount(request);
        return ApiResponse.success(request.getAccount()+ "를 성공적으로 삭제했습니다.");
    }


}
