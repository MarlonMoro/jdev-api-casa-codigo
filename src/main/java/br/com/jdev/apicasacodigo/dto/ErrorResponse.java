package br.com.jdev.apicasacodigo.dto;

public class ErrorResponse {

  private final String message;
  private final Object details;

  public ErrorResponse(String message, Object details) {
    this.message = message;
    this.details = details;
  }

  public String getMessage() {
    return message;
  }

  public Object getDetails() {
    return details;
  }
}
