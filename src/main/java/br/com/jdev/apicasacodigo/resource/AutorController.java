package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.AutorRequest;
import br.com.jdev.apicasacodigo.dto.AutorResponse;
import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.util.ProibeEmailDuplicadoValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/autor")
public class AutorController {

  @PersistenceContext
  EntityManager entityManager;
  @Autowired
  private ProibeEmailDuplicadoValidator proibeEmailDuplicadoAutorValidator;

  @InitBinder
  public void init(WebDataBinder dataBinder){
    dataBinder.addValidators(proibeEmailDuplicadoAutorValidator);

  }

  @Transactional
  @PostMapping
  public ResponseEntity<?> createAutor(@RequestBody @Valid AutorRequest request) {
    Autor autor = request.toModel();
    entityManager.persist(autor);
    return ResponseEntity.ok(AutorResponse.getInstance(autor));
  }

}
