package org.airtribe.assignment.taskmaster.auth.exception;

public class TokenExpiredException extends Exception {
  public TokenExpiredException(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  @Override
  public String getLocalizedMessage() {
    return super.getLocalizedMessage();
  }

  @Override
  public synchronized Throwable getCause() {
    return super.getCause();
  }
}
