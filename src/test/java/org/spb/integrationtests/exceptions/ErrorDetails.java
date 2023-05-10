package org.spb.integrationtests.exceptions;


import software.amazon.awssdk.utils.ToString;

import java.io.Serializable;

public class ErrorDetails implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String errorMessage;

  private final String errorCode;
  private final SPBErrors errors;

  private final String probableCause;

  protected ErrorDetails(Builder b) {
    this.probableCause = b.probableCause();
    this.errors = b.errors();
    this.errorCode = b.errors().getId();
    this.errorMessage = b.errors().getMsg();
  }

  /** @return {@link Builder} instance to construct a new {@link ErrorDetails}. */
  public static Builder builder() {
    return new BuilderImpl();
  }

  public static Class<? extends Builder> serializableBuilderClass() {
    return BuilderImpl.class;
  }

  public String probableCause() {
    return probableCause;
  }

  private SPBErrors errors() {
    return errors;
  }

  /** @return the human-readable error message provided by the service. */
  public String errorMessage() {
    return errorMessage;
  }

  /** Returns the error code associated with the response. */
  public String errorCode() {
    return errorCode;
  }

  /**
   * Create a {@link Builder} initialized with the properties of this {@code
   * ErrorDetails}.
   *
   * @return A new builder initialized with this config's properties.
   */
  public Builder toBuilder() {
    return new BuilderImpl(this);
  }

  @Override
  public String toString() {
    return ToString.builder("ErrorDetails")
        .add("errorMessage", errorMessage)
        .add("errorCode", errorCode)
        .add("probableCause", probableCause)
        .build();
  }

  public interface Builder {
    Builder probableCause(String probableCause);

    String probableCause();

    SPBErrors errors();

    Builder errors(SPBErrors errors);

    /**
     * Creates a new {@link ErrorDetails} with the properties set on this builder.
     *
     * @return The new {@link ErrorDetails}.
     */
    ErrorDetails build();
  }

  protected static final class BuilderImpl implements Builder {

    private String probableCause;
    private SPBErrors errors;

    private BuilderImpl() {}

    private BuilderImpl(ErrorDetails errorDetails) {
      this.errors = errorDetails.errors();
      this.probableCause = errorDetails.probableCause();
    }

    @Override
    public Builder probableCause(String probableCause) {
      this.probableCause = probableCause;
      return this;
    }

    @Override
    public String probableCause() {
      return probableCause;
    }

    @Override
    public SPBErrors errors() {
      return errors;
    }

    @Override
    public Builder errors(SPBErrors errors) {
      this.errors = errors;
      return this;
    }

    @Override
    public ErrorDetails build() {

      return new ErrorDetails(this);
    }
  }
}
