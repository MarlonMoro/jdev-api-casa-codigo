package br.com.jdev.apicasacodigo.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidator implements ConstraintValidator<UniqueValue, Object> {

  private String fieldName;
  private Class<?> domainClass;
  @PersistenceContext
  private EntityManager em;

  @Override
  public void initialize(UniqueValue constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
    this.domainClass = constraintAnnotation.domainClass();

  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    if(o == null || o.toString().isEmpty()){
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate("O campo " + fieldName + " não pode estar em branco.").addConstraintViolation();
      return false;
    }
    Query query = em.createQuery("select d from " + domainClass.getSimpleName() + " d where " + fieldName + " = ?1");
    query.setParameter(1, o);


    List<?> resultList = query.getResultList();

    boolean isValid = resultList.isEmpty();

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate("Já existe um " + fieldName + " cadastrado.").addConstraintViolation();
    }

    return isValid;
  }
}
