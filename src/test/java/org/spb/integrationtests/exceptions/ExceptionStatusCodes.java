package org.spb.integrationtests.exceptions;

public enum ExceptionStatusCodes {
  INFO(1),
  WARNING(2),
  ERROR(3),
  FATAL(4);
  private final int statusCode;

  ExceptionStatusCodes(int i) {
    this.statusCode = i;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
