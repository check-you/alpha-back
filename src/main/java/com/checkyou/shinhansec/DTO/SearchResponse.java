package com.checkyou.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchResponse {
    private Boolean success;
    private String message;
    private Data data;
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Data{
        private String name;
        private String bank;
        private String account;
        private String belong;
        private String businessCode;
        private List<Transaction> transactionList;
    }


}
