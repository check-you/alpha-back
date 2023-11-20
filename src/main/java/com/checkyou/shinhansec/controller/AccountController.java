package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.DTO.AccountDeleteRequestDTO;
import com.checkyou.shinhansec.DTO.AccountRequestDTO;
import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.service.MemberService;
import com.checkyou.shinhansec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final MemberService memberService;
    private final SearchService searchService;
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
