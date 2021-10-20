package com.fintech.ledger.request;


import com.fintech.ledger.exception.LedgerException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoan {

  //LOAN BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST

  private String bankName;
  private String borrowerName;
  private Double principal;
  private Double years;
  private Double rateOfInterest;


  void validate() {
    if (bankName.isEmpty() || borrowerName.isEmpty() || principal.isNaN() || years.isNaN()
        || rateOfInterest.isNaN()) {
      throw new LedgerException("invalid loan inputs");
    }
  }

  public static CreateLoan from(String[] input) {
    CreateLoan createLoan = CreateLoan.builder().bankName(input[1]).borrowerName(input[2])
        .principal(Double.valueOf(input[3])).years(Double.valueOf(input[4]))
        .rateOfInterest(Double.valueOf(input[5])).build();
    createLoan.validate();
    return createLoan;


  }
}
