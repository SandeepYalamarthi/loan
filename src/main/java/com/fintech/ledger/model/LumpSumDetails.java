package com.fintech.ledger.model;


import com.fintech.ledger.exception.LedgerException;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter

public class LumpSumDetails {

  //  //BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST
//  private String bankName;
//  private String borrowerName;
//  private Double principal;
//  private Double years;
//  private Double rateOfInterest;
//  private Double interest;
//  private Double total;
//  private Integer emiAmount;
//  private Double outStanding;
  private Double lumpSum;
  private Integer emiAfter;


}
