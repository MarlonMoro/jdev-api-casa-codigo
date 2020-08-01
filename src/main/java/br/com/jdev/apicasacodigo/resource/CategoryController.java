package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.CategoryRequest;
import br.com.jdev.apicasacodigo.model.Category;
import br.com.jdev.apicasacodigo.repository.CategoryRepository;
import br.com.jdev.apicasacodigo.resource.validators.CategoryValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/categories")
@RestController
public class CategoryController {

  @Autowired
  private CategoryValidator categoryValidator;
  @Autowired
  private CategoryRepository categoryRepository;

  @InitBinder
  public void init(WebDataBinder binder){
    binder.addValidators(categoryValidator);
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(
      @RequestBody @Valid CategoryRequest categoryRequest) {

    Category category = categoryRepository.save(categoryRequest.toModel());

    return ResponseEntity.ok(category);


  }

}
