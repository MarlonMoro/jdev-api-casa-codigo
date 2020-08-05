package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.ResponseError;
import br.com.jdev.apicasacodigo.dto.ValidationErrorsDto;
import br.com.jdev.apicasacodigo.exceptions.NotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> businnesError(NotFoundException ex) {

    return new ResponseEntity<>(
        new ResponseError(ex.getMessage(), String.valueOf(ex.getHttpStatus().value())),
        ex.getHttpStatus());

  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ValidationErrorsDto validationErrors(MethodArgumentNotValidException ex){
    List<ObjectError> erros = ex.getBindingResult().getAllErrors();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

    return buildValidationErrorDto(erros, fieldErrors);
  }

  private ValidationErrorsDto buildValidationErrorDto(List<ObjectError> errors, List<FieldError> fieldErrors){
    ValidationErrorsDto validationErrorsDto = new ValidationErrorsDto();
    fieldErrors.forEach(fieldError -> {
      validationErrorsDto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
    });
    return validationErrorsDto;
  }

}
