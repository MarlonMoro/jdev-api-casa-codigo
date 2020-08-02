package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.CategoryRequest;
import br.com.jdev.apicasacodigo.model.Category;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

  @PersistenceContext
  private EntityManager em;

  @Transactional
  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryRequest request){
    Category category = request.toModel();
    em.persist(category);
    return ResponseEntity.ok(category);

  }

}
