package com.kids.alpha.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInRequestDTo {
    private String accountNumber;
    private String password;
}
