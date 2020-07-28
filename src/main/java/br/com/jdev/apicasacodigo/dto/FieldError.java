package br.com.jdev.apicasacodigo.dto;

import lombok.Getter;

@Getter
public class FieldError {

  private String field;
  private String message;

  public FieldError() {
  }

  public FieldError(String field, String message) {
    this.field = field;
    this.message = message;
  }
}
