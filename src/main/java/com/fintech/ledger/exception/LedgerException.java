package com.fintech.ledger.exception;

public class LedgerException extends RuntimeException{

  public LedgerException() {
    super();
  }

  public LedgerException(String message) {
    super(message);
  }

  public LedgerException(String message, Throwable cause) {
    super(message, cause);
  }

  public LedgerException(Throwable cause) {
    super(cause);
  }

  protected LedgerException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
