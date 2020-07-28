package br.com.jdev.apicasacodigo.resource.validators;

import br.com.jdev.apicasacodigo.repository.AutorRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private AutorRepository autorRepository;
  @Override
  public void initialize(UniqueEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    boolean exist = autorRepository.findByEmail(s).isPresent();
    return !exist;
  }
}
