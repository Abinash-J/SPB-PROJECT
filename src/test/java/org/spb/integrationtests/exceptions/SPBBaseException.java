/*
 * Developed by Vijay Raghavan on 25/04/2`, 3:29 PM
 * Last modified on 25/04/2021, 3:29 PM
 * Copyright (c) 2021. Spectrum7 and/or its affiliates. All rights reserved.
 * Spectrum7 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.spb.integrationtests.exceptions;
/** Base exception for SPB project. All other exceptions must extend this exception */
public class SPBBaseException extends SdkException {

  private static final long serialVersionUID = 842666754992805811L;
  private final String requestId;
  private final String extendedRequestId;
  private final int statusCode;

  protected SPBBaseException(Builder b) {
    super(b);
    this.requestId = b.requestId();
    this.extendedRequestId = b.extendedRequestId();
    this.statusCode = b.statusCode();
  }

  /**
   * @return {@link Builder} instance to construct a new {@link SPBBaseException}.
   */
  public static Builder builder() {
    return new BuilderImpl();
  }

  public static Class<? extends Builder> serializableBuilderClass() {
    return BuilderImpl.class;
  }

  /**
   * The requestId that was returned by the called service.
   *
   * @return String containing the requestId
   */
  public String requestId() {
    return requestId;
  }

  /**
   * The extendedRequestId that was returned by the called service.
   *
   * @return String ctontaining the extendedRequestId
   */
  public String extendedRequestId() {
    return extendedRequestId;
  }

  /**
   * The status code that was returned by the called service.
   *
   * @return int containing the status code.
   */
  public int statusCode() {
    return statusCode;
  }

  /**
   * Create a {@link Builder} initialized with the properties of this {@code
   * SPBBaseException}.
   *
   * @return A new builder initialized with this config's properties.
   */
  @Override
  public Builder toBuilder() {
    return new BuilderImpl(this);
  }

  public interface Builder extends SdkException.Builder {
    Builder message(String message);

    Builder cause(Throwable cause);

    /**
     * Specifies the requestId returned by the called service.
     *
     * @param requestId A string that identifies the request made to a service.
     * @return This object for method chaining.
     */
    Builder requestId(String requestId);

    /**
     * The requestId returned by the called service.
     *
     * @return String containing the requestId
     */
    String requestId();

    /**
     * Specifies the extendedRequestId returned by the called service.
     *
     * @param extendedRequestId A string that identifies the request made to a service.
     * @return This object for method chaining.
     */
    Builder extendedRequestId(String extendedRequestId);

    /**
     * The extendedRequestId returned by the called service.
     *
     * @return String containing the extendedRequestId
     */
    String extendedRequestId();

    /**
     * Specifies the status code returned by the service.
     *
     * @param statusCode an int containing the status code returned by the service.
     * @return This method for object chaining.
     */
    Builder statusCode(int statusCode);

    /**
     * The status code returned by the service.
     *
     * @return int containing the status code
     */
    int statusCode();

    /**
     * Creates a new {@link SPBBaseException} with the specified properties.
     *
     * @return The new {@link SPBBaseException}.
     */
    @Override
    SPBBaseException build();
  }

  protected static class BuilderImpl extends SdkException.BuilderImpl
      implements Builder {

    protected String requestId;
    protected String extendedRequestId;
    protected int statusCode;

    protected BuilderImpl() {}

    protected BuilderImpl(SPBBaseException ex) {
      super(ex);
      this.requestId = ex.requestId();
      this.extendedRequestId = ex.extendedRequestId();
      this.statusCode = ex.statusCode();
    }

    @Override
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public Builder cause(Throwable cause) {
      this.cause = cause;
      return this;
    }

    @Override
    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    @Override
    public Builder extendedRequestId(String extendedRequestId) {
      this.extendedRequestId = extendedRequestId;
      return this;
    }

    @Override
    public String requestId() {
      return requestId;
    }

    public String getRequestId() {
      return requestId;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }

    @Override
    public String extendedRequestId() {
      return extendedRequestId;
    }

    public String getExtendedRequestId() {
      return extendedRequestId;
    }

    public void setExtendedRequestId(String extendedRequestId) {
      this.extendedRequestId = extendedRequestId;
    }

    @Override
    public Builder statusCode(int statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    public int getStatusCode() {
      return statusCode;
    }

    public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
    }

    @Override
    public int statusCode() {
      return statusCode;
    }

    @Override
    public SPBBaseException build() {
      return new SPBBaseException(this);
    }
  }
}
