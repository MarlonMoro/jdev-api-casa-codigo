package br.com.jdev.apicasacodigo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class NotFoundException extends RuntimeException{

  private String message;
  private HttpStatus httpStatus;

  public NotFoundException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
