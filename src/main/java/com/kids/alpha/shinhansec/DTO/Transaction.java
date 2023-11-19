package com.kids.alpha.shinhansec.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Transaction {

    private LocalDateTime transactionTime;  // 거래일자
    private String category;                // 구분 ( 국내주식매도 / 국내주식매수/ 해외선물매수 ...
    private String price;                   // 매수 / 매도 단가 (평단가)
    private String earningRate;             // 수익률
}
