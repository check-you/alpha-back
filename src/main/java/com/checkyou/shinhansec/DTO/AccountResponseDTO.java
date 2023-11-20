package com.checkyou.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountResponseDTO {
    private int listCnt;
    private List<AccountList> accountList;
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AccountList {
        private String account;
        private String bank;
        private String category;
    }
}
