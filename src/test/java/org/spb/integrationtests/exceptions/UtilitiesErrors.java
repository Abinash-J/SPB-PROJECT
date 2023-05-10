package org.spb.integrationtests.exceptions;



public enum UtilitiesErrors implements SPBErrors {
  INVALID_PARAMETER("U-001", "Invalid parameter has been provided"),
  DECRYPTION_FAILURE("U-002", "Unable to decrypt with the provided key"),
  SERVICE_DOWN("U-003", "Requested service seems to be down"),
  INVALID_REQUEST("U-004", "Invalid request."),
  RESOURCE_NOT_FOUND("U-005", "Requested resource was not found"),
  UNABLE_TO_CREATE_DATASOURCE("U-006", "Unable to create datasource"),
  UNABLE_TO_GET_DB_CONNECTION(
      "U-007", "Unable to create a database connection from connection pool"),
  PARAMETER_IS_NULL("U-008", "The required parameter is null");
  private final String id;
  private final String msg;

  UtilitiesErrors(String id, String msg) {
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
