package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.DetalheSiteLivroResponse;
import br.com.jdev.apicasacodigo.model.Livro;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheLivroController {

  @PersistenceContext
  private EntityManager manager;

  @GetMapping(path = "/produtos/{id}")
  public ResponseEntity<DetalheSiteLivroResponse> getLivros(@PathVariable("id") Long id) {

    Livro livro = manager.find(Livro.class, id);

    DetalheSiteLivroResponse detalheLivroResponse = new DetalheSiteLivroResponse(livro);
    return ResponseEntity.ok(detalheLivroResponse);
  }

}
