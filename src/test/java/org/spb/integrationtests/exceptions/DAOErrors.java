package org.spb.integrationtests.exceptions;



public enum DAOErrors implements SPBErrors {
  ERROR_IN_RETRIVING_DATA_DAO_LAYER("U-001", "Error in Retriving Data in DAO Layer"),
  ERROR_IN_ADDING_DATA_DAO_LAYER("U-002", "Error on Adding the Data in DAO Layer"),
  ERROR_IN_RETRIVING_RECORD_BY_ID_DAO_LAYER(
      "U-003", "Error on Retriving the Data by ID in DAO Layer"),
  ERROR_IN_DELETEING_RECORD_BY_ID_DAO_LAYER(
      "U-004", "Error in Deleting the Record by ID in DAO Layer"),
  ERROR_IN_UPDATING_RECORD_BY_ID_DAO_LAYER(
      "U-005", "Error in Updating the Record by ID in DAO Layer"),
  ERROR_IN_CHECKING_NAME_CODE_EXISTS_DAO_LAYER(
      "U-006", "Error in Checking the Name OR Code Exists or Not in DAO Layer"),
  ERROR_IN_CHECKING_NAME_CODE_EXISTS_BY_ID_DAO_LAYER(
      "U-007", "Error in Checking the Name OR Code Exists or Not By ID in DAO Layer"),
  ERROR_IN_HARD_DELETEING_RECORD_BY_ID_DAO_LAYER(
      "U-004", "Error in Deleting the Record by ID in DAO Layer"),
  ;
  private final String id;
  private final String msg;

  DAOErrors(String id, String msg) {
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
