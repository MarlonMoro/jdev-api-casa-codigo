package br.com.jdev.apicasacodigo.dto;

import lombok.Getter;

@Getter
public class ResponseError {

  private String message;
  private String status;

  public ResponseError(String message, String status) {
    this.message = message;
    this.status = status;
  }
}
