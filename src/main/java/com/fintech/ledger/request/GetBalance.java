package com.fintech.ledger.request;

import com.fintech.ledger.exception.LedgerException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetBalance {

//  BALANCE BANK_NAME BORROWER_NAME EMI_NO

  private String bankName;
  private String borrowerName;
  private Integer emiNumber;

  private void validate() {
    if (bankName.isEmpty() || borrowerName.isEmpty() || emiNumber == null) {
      throw new LedgerException("invalid get balance inputs");
    }
  }

  public static GetBalance from(String[] input) {
    GetBalance getBalance = GetBalance.builder().bankName(input[1]).borrowerName(input[2])
        .emiNumber(Integer.valueOf(input[3])).build();
    getBalance.validate();
    return getBalance;
  }


}
