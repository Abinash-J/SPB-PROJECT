package org.spb.integrationtests.exceptions;

public class SdkException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  protected SdkException(Builder builder) {
    super(messageFromBuilder(builder), builder.cause());
  }

  /**
   * Use the message from the builder, if it's specified, otherwise inherit the message from the
   * "cause" exception.
   */
  private static String messageFromBuilder(Builder builder) {
    if (builder.message() != null) {
      return builder.message();
    }

    if (builder.cause() != null) {
      return builder.cause().getMessage();
    }

    return null;
  }

  public static SdkException create(String message, Throwable cause) {
    return SdkException.builder().message(message).cause(cause).build();
  }

  /** @return {@link Builder} instance to construct a new {@link SdkException}. */
  public static Builder builder() {
    return new BuilderImpl();
  }

  /** Specifies whether or not an exception can be expected to succeed on a retry. */
  public boolean retryable() {
    return false;
  }

  /**
   * Create a {@link Builder} initialized with the properties of this {@code
   * SdkException}.
   *
   * @return A new builder initialized with this config's properties.
   */
  public Builder toBuilder() {
    return new BuilderImpl(this);
  }

  public interface Builder extends Buildable {
    /**
     * Specifies the exception that caused this exception to occur.
     *
     * @param cause The exception that caused this exception to occur.
     * @return This object for method chaining.
     */
    Builder cause(Throwable cause);

    /**
     * The exception that caused this exception to occur.
     *
     * @return The exception that caused this exception to occur.
     */
    Throwable cause();

    /**
     * Specifies the details of this exception.
     *
     * @param message The details of this exception.
     * @return This method for object chaining
     */
    Builder message(String message);

    /**
     * The details of this exception.
     *
     * @return Details of this exception.
     */
    String message();

    /**
     * Creates a new {@link SdkException} with the specified properties.
     *
     * @return The new {@link SdkException}.
     */
    @Override
    SdkException build();
  }

  protected static class BuilderImpl implements Builder {

    protected Throwable cause;
    protected String message;

    protected BuilderImpl() {}

    protected BuilderImpl(SdkException ex) {
      this.cause = ex.getCause();
      this.message = ex.getMessage();
    }

    public Throwable getCause() {
      return cause;
    }

    public void setCause(Throwable cause) {
      this.cause = cause;
    }

    @Override
    public Builder cause(Throwable cause) {
      this.cause = cause;
      return this;
    }

    @Override
    public Throwable cause() {
      return cause;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    @Override
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public String message() {
      return message;
    }

    public SdkException build() {
      return new SdkException(this);
    }
  }
}
