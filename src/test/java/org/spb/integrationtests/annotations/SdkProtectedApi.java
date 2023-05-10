package org.spb.integrationtests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marker for elements that should only be accessed by the generated clients and not users of the
 * SDK.
 *
 * <p>{@code @InternalApi} classes.
 */
@Target({
  ElementType.PACKAGE,
  ElementType.TYPE,
  ElementType.FIELD,
  ElementType.CONSTRUCTOR,
  ElementType.METHOD
})
@software.amazon.awssdk.annotations.SdkProtectedApi
public @interface SdkProtectedApi {}
