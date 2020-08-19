package br.com.jdev.apicasacodigo.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

  private final HttpStatus status;
  private final String message;
  private final Object objectError;

  public BusinessException(HttpStatus status, String message, Object objectError) {
    this.status = status;
    this.message = message;
    this.objectError = objectError;
  }

  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public Object getObjectError() {
    return objectError;
  }
}
