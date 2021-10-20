package com.fintech.ledger.dao;

import com.fintech.ledger.model.Loan;
import com.fintech.ledger.model.LoanKey;
import java.util.HashMap;
import java.util.Map;

public class LoanDao {

  //key is borrowername,bankanem
  private static final Map<LoanKey, Loan> loanMap = new HashMap<>();


  public static void createLoan(LoanKey loanKey, Loan loan) {
    loanMap.put(loanKey, loan);
  }

  public static Loan getLoan(LoanKey loanKey) {
    return loanMap.get(loanKey);
  }

  public static void updateLoanAmount(LoanKey loanKey, Double lumpSum, Integer emiAfter) {
    Loan loan = loanMap.get(loanKey);
    loan.updatePayment(lumpSum, emiAfter);

  }

}
