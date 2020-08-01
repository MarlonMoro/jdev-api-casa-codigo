package br.com.jdev.apicasacodigo.resource.validators;

import br.com.jdev.apicasacodigo.dto.CategoryRequest;
import br.com.jdev.apicasacodigo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return CategoryRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CategoryRequest categoryRequest = (CategoryRequest) o;

    categoryRepository.findByName(categoryRequest.getName()).ifPresent((category) -> {
      errors.rejectValue("Categoria já cadastrada", null,
          String.format("Categoria %s já cadastrada", categoryRequest.getName()));
    });


  }
}
