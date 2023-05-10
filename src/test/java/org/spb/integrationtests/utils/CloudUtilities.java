/*
 * Developed by Vijay Raghavan on 26/04/21 7:19 PM
 * Last modified on 26/04/21 7:19 PM
 * Copyright (c) 2021. Spectrum7 and/or its affiliates. All rights reserved.
 * Spectrum7 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.spb.integrationtests.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spb.integrationtests.exceptions.*;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.*;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.InvalidKeyIdException;
import software.amazon.awssdk.services.ssm.model.Parameter;
import software.amazon.awssdk.services.ssm.model.ParameterNotFoundException;

import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Utility class for all cloud related functions. Access to cloud and related functions will be
 * available here. Utility functions for accessing and handling cloud related operations.
 */



public class CloudUtilities {
  private static final Logger logger = LogManager.getLogger(CloudUtilities.class);
  private String accessKey = null;
  private String secretKey = null;
  private String region=null;

  public CloudUtilities() {
    String aKey = System.getenv("ACCESS_KEY");
    String sKey = System.getenv("SECRET_KEY");
    String r=System.getenv("REGION");
    if (aKey != null && sKey != null && r!=null ) {
      this.accessKey = aKey;
      this.secretKey = sKey;
      this.region=r;
    }
  }

  public void setKeys(String accessKey, String privateKey) {
    this.accessKey = accessKey;
    this.secretKey = privateKey;
  }

  /**
   * Retrieve secret stored in AWS Secrets Manager.
   *
   * @param name The key name.
   * @return The secret's value. If it is encoded, the decoded string will be returned.
   * @exception {@link UtilitiesException} is thrown if the secret cannot be retrieved.
   */
  public String getSecret(String name) {
    // Create a Secrets Manager client
    SecretsManagerClient client = (SecretsManagerClient) getClient(AWSServices.SECRETS_MANAGER);
    String secret;
    GetSecretValueRequest getSecretValueRequest =
        GetSecretValueRequest.builder().secretId(name).build();
    GetSecretValueResponse getSecretValueResponse;
    try {
      if (client != null) {
        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
      } else {
        ErrorDetails e =
            ErrorDetails.builder()
                .errors(Errors.NULL_VALUE)
                .probableCause(
                    "Access issues could possibly prevent obtaining an instance for Secrets Manager client")
                .build();
        throw UtilitiesException.builder()
            .errorDetails(e)
            .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
            .cause(new NullPointerException("Secrets Manager client is null"))
            .build();
      }
    } catch (DecryptionFailureException e) {
      ErrorDetails error =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.DECRYPTION_FAILURE)
              .probableCause(
                  "You might be specifying a KMS key that is managed by the account while the Secret might be using a key managed by AWS")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(error)
          .cause(e)
          .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
          .build();
    } catch (InternalServiceErrorException e) {
      ErrorDetails errorDetails =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.SERVICE_DOWN)
              .probableCause("Secrets Manager service could be down")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .cause(e)
          .statusCode(ExceptionStatusCodes.FATAL.getStatusCode())
          .message("Please check if Secrets Manager Service of AWS is down")
          .build();
    } catch (InvalidParameterException e) {
      ErrorDetails errorDetails =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.INVALID_PARAMETER)
              .probableCause(
                  "Wrong parameter could have been provided. The key for the secret provided is: "
                      + name
                      + " .Please check if this is correct")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .cause(e)
          .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
          .build();
    } catch (InvalidRequestException e) {
      ErrorDetails errorDetails =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.INVALID_REQUEST)
              .probableCause(
                  "The lookup value does not seem to be correct for the current state. Is the secret currently being rotated?")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .cause(e)
          .statusCode(ExceptionStatusCodes.FATAL.getStatusCode())
          .build();
    } catch (ResourceNotFoundException e) {
      ErrorDetails errorDetails =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.RESOURCE_NOT_FOUND)
              .probableCause("The specified key was not found. Has this key been deleted?")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .cause(e)
          .statusCode(ExceptionStatusCodes.FATAL.getStatusCode())
          .build();
    }
    if (getSecretValueResponse.secretString() != null) {
      secret = getSecretValueResponse.secretString();
    } else {
      secret =
          new String(
              Base64.getDecoder()
                  .decode(getSecretValueResponse.secretBinary().asByteBuffer())
                  .array());
    }

    return secret;
  }

  /**
   * Get parameter value specified by key
   *
   * @param key The parameter that should be looked up
   * @return The value associated with the parameter. Returns null if the key is not found
   * @exception {@link UtilitiesException} is thrown if the parameter cannot be retrieved.
   */
  public String getParameter(String key) {
    String parameterValue = null;

    try (SsmClient ssmClient = (SsmClient) getClient(AWSServices.SSM)) {
      GetParameterRequest getParameterRequest =
          GetParameterRequest.builder().name(key).withDecryption(true).build();
      try {
        if (ssmClient != null) {
          Parameter parameter = ssmClient.getParameter(getParameterRequest).parameter();
          if (parameter != null) {
            parameterValue = parameter.value();
          }
        } else {
          ErrorDetails e =
              ErrorDetails.builder()
                  .errors(Errors.NULL_VALUE)
                  .probableCause(
                      "Access issues could possibly prevent obtaining an instance for SSM client")
                  .build();
          throw UtilitiesException.builder()
              .errorDetails(e)
              .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
              .cause(new NullPointerException("SSM client is null"))
              .build();
        }

      } catch (ParameterNotFoundException | InvalidKeyIdException e) {
        ErrorDetails errorDetails =
            ErrorDetails.builder()
                .errors(UtilitiesErrors.INVALID_PARAMETER)
                .probableCause("Wrong key has been provided")
                .build();
        throw UtilitiesException.builder()
            .errorDetails(errorDetails)
            .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
            .cause(e)
            .build();
      }
      return parameterValue;
    }
  }

  /**
   * Get AWS client for services. This is a generic method and returns different clients that would
   * need to be cast based on the requested service type
   *
   * @param service {@link AWSServices} specifies the currently supported list of services
   * @return The appropriate AWS client for the requested service.
   */
  private SdkClient getClient(AWSServices service) {
    if (region == null) {
      ErrorDetails errorDetails = ErrorDetails.builder().errors(CloudErrors.REGION_IS_NULL).build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
          .build();
    }
    AwsCredentials credentials = null;
    if (this.accessKey == null || this.secretKey == null) {
      InstanceProfileCredentialsProvider ipcp = InstanceProfileCredentialsProvider.create();
      credentials = ipcp.resolveCredentials();
      ipcp.close();
    }
    if (this.accessKey != null && this.secretKey != null) {
      credentials = AwsBasicCredentials.create(accessKey, secretKey);
    }
    return getClient(service, credentials);
  }

  /**
   * Internal method to resolve services and obtain client based on credentials provided
   *
   * @param service {@link AWSServices} specifies the currently supported list of services
   * @param credentials Set of credentials to create the service with.
   * @return The appropriate AWS service client
   */
  private SdkClient getClient(AWSServices service, AwsCredentials credentials) {
    SdkClient client = null;
    Region awsRegion = null;
    if (region != null) {
      awsRegion = Region.of(region);
    } else {
      ErrorDetails errorDetails =
          ErrorDetails.builder()
              .errors(UtilitiesErrors.PARAMETER_IS_NULL)
              .probableCause("Region value is null")
              .build();
      throw UtilitiesException.builder()
          .errorDetails(errorDetails)
          .cause(new NullPointerException("Region field is null"))
          .statusCode(ExceptionStatusCodes.ERROR.getStatusCode())
          .build();
    }
    switch (service) {
      case SECRETS_MANAGER:
        client =
            SecretsManagerClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(awsRegion)
                .build();
        break;
      case SSM:
        SsmClient.builder()
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .region(awsRegion)
            .build();
        break;
    }
    return client;
  }

  public static void main(String[] args) throws JsonProcessingException {
    CloudUtilities c = new CloudUtilities();
    String s = c.getSecret("qa/db/integration");
    System.out.println(s);
  
    ObjectMapper mapper = new ObjectMapper();
    ConnectionPOJO connectionPOJO = mapper.readValue(s,ConnectionPOJO.class);
    System.out.println(connectionPOJO.getPassword());
    StringBuilder sql = new StringBuilder();
    String tableName ="legal_entities";
    HashMap<String, String> values = new HashMap<>();
    values.put("GROUP_ID","123");
    values.put("LEGAL_ENTITY_CODE","abc");
    values.put("LEGAL_ENTITY_NAME","name");
    sql.append("INSERT INTO spb_schema."+tableName+" (")
            .append(values.keySet().stream().collect(Collectors.joining(",")))
            .append(")")
            .append(" VALUES (")
            .append(values.values().stream().map(plain -> '"' + StringEscapeUtils.escapeJava(plain)+'"').collect(Collectors.joining(",")))
            .append(")");
    System.out.println(sql);
  }
}
