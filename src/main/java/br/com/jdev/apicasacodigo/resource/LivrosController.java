package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.NovoLivroRequest;
import br.com.jdev.apicasacodigo.model.Livro;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivrosController {


  @PersistenceContext
  private EntityManager manager;

  @Transactional
  @PostMapping(path = "/livros")
  public ResponseEntity<?> crairLivro(@RequestBody @Valid NovoLivroRequest livroRequest) {

    Livro livro = livroRequest.toModel(manager);
    manager.persist(livro);
    return ResponseEntity.ok(livro);
  }

}
