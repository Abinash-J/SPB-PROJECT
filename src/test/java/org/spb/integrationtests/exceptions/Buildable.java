package org.spb.integrationtests.exceptions;


import org.spb.integrationtests.annotations.SdkProtectedApi;

@SdkProtectedApi
public interface Buildable {
  Object build();
}
