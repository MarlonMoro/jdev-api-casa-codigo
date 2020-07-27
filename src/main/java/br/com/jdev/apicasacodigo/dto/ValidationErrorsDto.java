package br.com.jdev.apicasacodigo.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ValidationErrorsDto {

  private String message;
  private List<FieldError> errors = new ArrayList<>();


  public ValidationErrorsDto() {
    this.message = "Existem campos inválidos na requisição";
  }


  public void addFieldError(String field, String message) {
    this.errors.add(new FieldError(field, message));
  }


}
