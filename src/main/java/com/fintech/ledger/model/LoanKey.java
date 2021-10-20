package com.fintech.ledger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanKey {

  private String bankName;
  private String borrowerName;

  @Override
  public int hashCode() {
    return (bankName + borrowerName).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof LoanKey) {
      LoanKey loanKey = (LoanKey) obj;
      return (this.borrowerName.equals(loanKey.getBorrowerName()) && this.bankName.equals(
          loanKey.getBankName()));
    }
    return false;
  }
}
