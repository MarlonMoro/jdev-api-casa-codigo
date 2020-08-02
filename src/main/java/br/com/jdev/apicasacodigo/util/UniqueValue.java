package br.com.jdev.apicasacodigo.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

  String message() default "Campo inserido já existe";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  String fieldName();

  Class<?> domainClass();


}
