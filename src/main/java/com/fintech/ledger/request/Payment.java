package com.fintech.ledger.request;

import com.fintech.ledger.exception.LedgerException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
//PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO

  private String bankName;
  private String borrowerName;
  private Double lumpSumAmount;
  private Integer emiNumber;


  private void validate() {
    if (bankName.isEmpty() || borrowerName.isEmpty() || lumpSumAmount == null || emiNumber==null) {
      throw new LedgerException("invalid payment  inputs");
    }
  }

  public static Payment from(String[] input) {
    return Payment.builder().bankName(input[1]).borrowerName(input[2]).lumpSumAmount(Double.valueOf(
        input[3])).emiNumber(Integer.valueOf(input[4])).build();
  }

}
