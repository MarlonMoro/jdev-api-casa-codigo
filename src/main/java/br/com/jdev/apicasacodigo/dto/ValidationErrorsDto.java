package br.com.jdev.apicasacodigo.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ValidationErrorsDto {

  private List<String> globalErrosMessage = new ArrayList<>();
  private List<FieldError> fieldErros = new ArrayList<>();

  public void addError(String error){
    this.globalErrosMessage.add(error);
  }

  public void addFiledError(String field, String message){
    this.fieldErros.add(new FieldError(field, message));
  }


}
