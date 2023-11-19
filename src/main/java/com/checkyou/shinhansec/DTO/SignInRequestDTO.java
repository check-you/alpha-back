package com.checkyou.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInRequestDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
}
