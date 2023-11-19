package com.checkyou.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchRequestDTO {
    private String name;
    private String bank;
    private String account;
    private String belong;
    private String businessCode;
}
