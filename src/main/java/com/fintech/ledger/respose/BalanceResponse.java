package com.fintech.ledger.respose;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BalanceResponse {

  //BANK_NAME BORROWER_NAME AMOUNT_PAID NO_OF_EMIS_LEFT
  private String bankName;
  private String borrowerName;
  private Integer amountPaid;
  private Integer remainingEmis;
}
