package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.AutorRequest;
import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.repository.AutorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

  @Autowired
  private AutorRepository autorRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return AutorRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object object, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    AutorRequest autorRequest = (AutorRequest) object;

    Optional<Autor> autorOpt = autorRepository.findByEmail(autorRequest.getEmail());

    autorOpt.ifPresent((autor) -> errors.rejectValue("email", null,
        String.format("Já existe autor com o email informado %s", autor.getEmail())));


  }
}
