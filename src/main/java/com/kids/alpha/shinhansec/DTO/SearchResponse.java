package com.kids.alpha.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchResponse {

    private String name;
    private String belong;
    private String account;
    private String businessCode;
    private List<Transaction> transactionList;


}
