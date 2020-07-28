package br.com.jdev.apicasacodigo.resource.validators;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

  String message() default "Email já cadastrado!";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };


  @Target(FIELD)
  @Retention(RUNTIME)
  @Documented
  @interface List {

  }


}
