package com.fintech.ledger.processors;

import com.fintech.ledger.exception.LedgerException;
import com.fintech.ledger.model.CommandType;
import com.fintech.ledger.request.CreateLoan;
import com.fintech.ledger.request.GetBalance;
import com.fintech.ledger.request.Payment;
import com.fintech.ledger.respose.BalanceResponse;
import com.fintech.ledger.service.LedgerService;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessor {

  private LedgerService ledgerService = new LedgerService();

  public void parseFile(Path path) {

    try {
      BufferedReader reader = Files.newBufferedReader(path);
      String line;
      while ((line = reader.readLine()) != null) {

        parseLine(line);

      }
    } catch (IOException e) {
      throw new LedgerException("unable to parse file", e);
    }
  }

  private void processLoan(String[] input) {
    log.info("creating loan");
    CreateLoan createLoan = CreateLoan.from(input);
    ledgerService.createLoan(createLoan);

  }


  private void processBalance(String[] input) {

    log.info("fetching balance");
    //BALANCE BANK_NAME BORROWER_NAME EMI_NO
    GetBalance getBalance = GetBalance.from(input);
    BalanceResponse balanceResponse = ledgerService.getLoanBalance(getBalance);

    System.out.println(balanceResponse.getBankName() + " " + balanceResponse.getBorrowerName() + " "
        + balanceResponse.getAmountPaid() + " " + balanceResponse.getRemainingEmis());

  }

  private void processPayment(String[] input) {
    log.info("doing payment loan");
    Payment payment = Payment.from(input);
    ledgerService.updatePayment(payment);


  }


  private void parseLine(String line) {

    String[] input = line.split(" ");
    CommandType commandType = CommandType.valueOf(input[0]);
    switch (commandType) {
      case LOAN:
        processLoan(input);
        break;
      case BALANCE:
        processBalance(input);
        break;
      case PAYMENT:
        processPayment(input);
        break;
    }

  }

}
