package org.spb.integrationtests.exceptions;



public class ServicesException extends SPBBaseException {

  private static final long serialVersionUID = 2320797311444331914L;
  private final ErrorDetails errorDetails;

  protected ServicesException(Builder b) {
    super(b);
    this.errorDetails = b.errorDetails();
  }

  /** @return {@link Builder} instance to construct a new {@link ServicesException}. */
  public static Builder builder() {
    return new BuilderImpl();
  }

  public static Class<? extends Builder> serializableBuilderClass() {
    return BuilderImpl.class;
  }

  /**
   * Additional details pertaining to an exception thrown by an AWS service.
   *
   * @return {@link ErrorDetails}.
   */
  public ErrorDetails errorDetails() {
    return errorDetails;
  }

  @Override
  public String getMessage() {
    if (errorDetails != null) {
      return errorDetails().errorMessage()
          + " Status Code: "
          + statusCode()
          + ", Request ID: "
          + requestId()
          + ", Extended Request ID: "
          + extendedRequestId()
          + ")";
    }

    return super.getMessage();
  }

  /**
   * Create a {@link Builder} initialized with the properties of this {@code
   * UtilitiesException}.
   *
   * @return A new builder initialized with this config's properties.
   */
  @Override
  public Builder toBuilder() {
    return new BuilderImpl(this);
  }

  public interface Builder extends SPBBaseException.Builder {

    /**
     * Specifies the additional errorDetails from the service response.
     *
     * @param errorDetails Object containing additional details from the response.
     * @return This object for method chaining.
     */
    Builder errorDetails(ErrorDetails errorDetails);

    /**
     * The {@link ErrorDetails} from the service response.
     *
     * @return {@link ErrorDetails}.
     */
    ErrorDetails errorDetails();

    @Override
    Builder message(String message);

    @Override
    Builder cause(Throwable t);

    @Override
    Builder requestId(String requestId);

    @Override
    Builder extendedRequestId(String extendedRequestId);

    @Override
    Builder statusCode(int statusCode);

    @Override
    ServicesException build();
  }

  protected static class BuilderImpl extends SPBBaseException.BuilderImpl implements Builder {

    protected ErrorDetails errorDetails;

    protected BuilderImpl() {}

    protected BuilderImpl(ServicesException ex) {
      super(ex);
      this.errorDetails = ex.errorDetails();
    }

    @Override
    public Builder errorDetails(ErrorDetails errorDetails) {
      this.errorDetails = errorDetails;
      return this;
    }

    @Override
    public ErrorDetails errorDetails() {
      return errorDetails;
    }

    public ErrorDetails getErrorDetails() {
      return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
      this.errorDetails = errorDetails;
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
    public Builder statusCode(int statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    @Override
    public ServicesException build() {
      return new ServicesException(this);
    }
  }
}
