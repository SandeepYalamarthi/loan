package com.fintech.ledger.model;


import com.fintech.ledger.exception.LedgerException;
import java.util.HashMap;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter

public class Loan {

  //BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST
  private String bankName;
  private String borrowerName;
  private Double principal;
  private Double years;
  private Double rateOfInterest;
  private Double interest;
  private Double total;
  private Integer emiAmount;
  private Double outStanding;
  private Integer noOfEmis;
  private Integer maxLempsumEmi;


  private HashMap<Integer, Double> lumpSumDetailsHashMap;


  public void updatePayment(Double lumpSum, Integer emiAfter) {
    if (lumpSum > outStanding) {
      throw new LedgerException("invalid input");
    } else {
      this.outStanding -= lumpSum+ emiAmount*emiAfter;
      this.noOfEmis -= new Double(Math.ceil(this.outStanding / emiAmount)).intValue();
      this.lumpSumDetailsHashMap.put(emiAfter, lumpSum);
    }
  }


}
