package org.spb.integrationtests.exceptions;


public enum CloudErrors implements SPBErrors {
  REGION_IS_NULL("C-001", "Region field is null. Region is mandatory");
  private final String id;
  private final String msg;

  CloudErrors(String id, String msg) {
    this.id = id;
    this.msg = msg;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getMsg() {
    return msg;
  }
}
