package br.com.jdev.apicasacodigo.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

  private String domainAttribute;
  private Class<?> klass;
  @PersistenceContext
  private EntityManager manager;

  @Override
  public void initialize(ExistsId constraintAnnotation) {
    domainAttribute = constraintAnnotation.fieldName();
    klass = constraintAnnotation.domainClass();

  }

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
    Query query = manager
        .createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
    query.setParameter("value", value);

    List<?> list = query.getResultList();
    Assert.isTrue(list.size() <= 1,
        "aconteceu algo bizarro e você tem mais de um " + klass + " com o atributo "
            + domainAttribute + " com o valor = " + value);

    return !list.isEmpty();
  }
}
