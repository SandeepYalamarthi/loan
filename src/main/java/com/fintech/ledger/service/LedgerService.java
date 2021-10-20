package com.fintech.ledger.service;

import com.fintech.ledger.dao.LoanDao;
import com.fintech.ledger.request.CreateLoan;
import com.fintech.ledger.request.Payment;
import com.fintech.ledger.respose.BalanceResponse;
import com.fintech.ledger.model.Loan;
import com.fintech.ledger.model.LoanKey;
import com.fintech.ledger.request.GetBalance;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LedgerService {


  public void createLoan(CreateLoan loanRequest) {
    LoanKey loanKey = new LoanKey(loanRequest.getBankName(), loanRequest.getBorrowerName());
    Double interest =
        loanRequest.getRateOfInterest() * loanRequest.getPrincipal() * loanRequest.getYears() / 100;
    Double total = loanRequest.getPrincipal() + interest;
    Integer numberOfEmis = new Double(Math.ceil(loanRequest.getYears() * 12)).intValue();
    Integer emi = new Double(
        Math.ceil(total / numberOfEmis.doubleValue())).intValue();
    Loan loan = Loan.builder().bankName(loanRequest.getBankName())
        .borrowerName(loanRequest.getBorrowerName())
        .principal(loanRequest.getPrincipal())
        .years(loanRequest.getYears())
        .rateOfInterest(loanRequest.getRateOfInterest())
        .interest(interest)
        .total(total)
        .emiAmount(emi)
        .outStanding(total).noOfEmis(numberOfEmis).maxLempsumEmi(0).lumpSumDetailsHashMap(new HashMap<>()).build();

    LoanDao.createLoan(loanKey, loan);
  }

  public BalanceResponse getLoanBalance(GetBalance getBalance) {
    LoanKey loanKey = new LoanKey(getBalance.getBankName(), getBalance.getBorrowerName());

    Loan loan = LoanDao.getLoan(loanKey);

    Double amountPaid;
    if (loan.getMaxLempsumEmi() >= getBalance.getEmiNumber()) {
      amountPaid = (double) (loan.getEmiAmount() * getBalance.getEmiNumber());
    } else {
      Double totalLumpsum = loan.getLumpSumDetailsHashMap().entrySet().stream()
          .filter(x -> x.getKey() < getBalance.getEmiNumber()).map(Entry::getValue)
          .reduce(0.0, Double::sum);
      amountPaid = totalLumpsum + loan.getEmiAmount() * getBalance.getEmiNumber();

    }
    Integer remainingEmis = loan.getNoOfEmis();

    return BalanceResponse.builder().bankName(loan.getBankName()).borrowerName(
            loan.getBorrowerName()).amountPaid(amountPaid.intValue()).remainingEmis(remainingEmis)
        .build();


  }

  public void updatePayment(Payment payment) {
    LoanKey loanKey = new LoanKey(payment.getBankName(), payment.getBorrowerName());

    LoanDao.updateLoanAmount(loanKey, payment.getLumpSumAmount(), payment.getEmiNumber());

  }

}
