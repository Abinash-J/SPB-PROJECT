package org.spb.integrationtests.utils;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/** Enum for the various AWS Services that are currently used in SpecChain. */
public enum AWSServices {
  EC2("EC2"),
  S3("S3"),
  ROUTE53("ROUTE53"),
  SSM("SSM"),
  IAM("IAM"),
  SNS("SNS"),
  SES("SES"),
  STS("STS"),
  DYNAMODB("DYNAMODB"),
  SECRETS_MANAGER("SECRETSMANAGER"),
  UNKNOWN(null);
  private final String value;

  AWSServices(String value) {
    this.value = value;
  }

  /**
   * Returns associated AWSService from the string value
   *
   * @param value String value of AWSService
   * @return AWSService
   */
  public static AWSServices fromValue(String value) {
    AWSServices result;
    if (value == null) {
      result = null;
    } else {
      result =
          Stream.of(AWSServices.values())
              .filter(e -> e.toString().equals(value.toLowerCase()))
              .findFirst()
              .orElse(UNKNOWN);
    }
    return result;
  }

  /**
   * Obtain a list of all services that are supported
   *
   * @return A set containing all the known AWSServices used by SpecChain
   */
  public static Set<AWSServices> knownValues() {
    return Stream.of(values()).filter(v -> v != UNKNOWN).collect(toSet());
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
