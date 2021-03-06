package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.NovaCompraRequest;
import br.com.jdev.apicasacodigo.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CupomValidoValidator implements Validator {

  @Autowired
  private CupomRepository cupomRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return NovaCompraRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    if(errors.hasErrors()){
      return;
    }

    NovaCompraRequest request = (NovaCompraRequest) o;

    request.getCodigoCupom()
        .flatMap(codigoCupom -> cupomRepository.findByCodigo(codigoCupom))
        .ifPresent(cupom -> {
          if (!cupom.isValid()) {
            errors.rejectValue("codigoCupom", null, "Cupom invalido");
          }
        });
  }
}
