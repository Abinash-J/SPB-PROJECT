package org.spb.integrationtests.exceptions;

public enum Errors implements SPBErrors {
  NULL_VALUE("C-001", "Specified object is null");
  private final String id;
  private final String msg;

  Errors(String id, String msg) {
    this.id = id;
    this.msg = msg;
  }

  /** @return Returns ID for the error */
  @Override
  public String getId() {
    return this.id;
  }

  /** @return Message for the error */
  @Override
  public String getMsg() {
    return this.msg;
  }
}
