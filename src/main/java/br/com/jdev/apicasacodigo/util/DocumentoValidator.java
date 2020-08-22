package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.CompraDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DocumentoValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return CompraDto.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    CompraDto compraDto = (CompraDto) o;
    if (ObjectUtils.isEmpty(compraDto.getDocumento())) {
      errors.rejectValue("documento null", null, "Um documento deve ser informado, CPF ou CNPJ");
    }

    if (!compraDto.documentoValido()) {
      errors.rejectValue("documento", null, "Um documento valido deve ser informado, CPF ou CNPJ");
    }


  }


}
