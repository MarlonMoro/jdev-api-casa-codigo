package br.com.jdev.apicasacodigo.resource;


import br.com.jdev.apicasacodigo.dto.ErrorResponse;
import br.com.jdev.apicasacodigo.dto.ValidationErrorsDto;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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


  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ValidationErrorsDto validationErrors(MethodArgumentNotValidException ex){
    List<ObjectError> erros = ex.getBindingResult().getAllErrors();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

    return buildValidationErrorDto(erros, fieldErrors);
  }

  private ValidationErrorsDto buildValidationErrorDto(List<ObjectError> errors,
      List<FieldError> fieldErrors) {
    ValidationErrorsDto validationErrorsDto = new ValidationErrorsDto();
    fieldErrors.forEach(fieldError -> {
      validationErrorsDto.addFiledError(fieldError.getField(), getMessageError(fieldError));
    });
    errors.forEach(error -> validationErrorsDto.addError(getMessageError(error)));
    return validationErrorsDto;
  }

  private String getMessageError(ObjectError objectError) {
    return messageSource.getMessage(objectError, new Locale("BB"));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> bussinessErrorHandler(BusinessException ex) {

    return ResponseEntity.status(ex.getStatus())
        .body(new ErrorResponse(ex.getMessage(), ex.getObjectError()));
  }


}
