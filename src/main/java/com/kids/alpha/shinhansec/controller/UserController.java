package com.kids.alpha.shinhansec.controller;

import com.kids.alpha.shinhansec.DTO.LogInRequestDTO;
import com.kids.alpha.shinhansec.DTO.SignInRequestDTo;
import com.kids.alpha.shinhansec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LogInRequestDTO userLoginRequestDto) throws Exception {
        return userService.login(userLoginRequestDto.getAccountNumber(), userLoginRequestDto.getPassword());
    }
    @PostMapping("/signIn")
    public ResponseEntity signin(@RequestBody SignInRequestDTo userSignInRequestDto) throws Exception {
        return userService.signin(userSignInRequestDto.getAccountNumber(), userSignInRequestDto.getPassword());
    }
}
