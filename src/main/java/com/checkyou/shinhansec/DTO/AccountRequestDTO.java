package com.checkyou.shinhansec.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountRequestDTO {
    private String bank;
    private String account;
}
