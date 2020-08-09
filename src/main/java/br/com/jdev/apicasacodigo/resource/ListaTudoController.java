package br.com.jdev.apicasacodigo.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListaTudoController {

  @PersistenceContext
  private EntityManager manager;


  @GetMapping(path = "/lista-tudo")
  public ResponseEntity<HashMap<String, Object>> list() {

    List autores = manager.createQuery("select a from Autor a").getResultList();

    HashMap<String, Object> result = new HashMap<>();
    result.put("autores", autores);

    List categorias = manager.createQuery("select c from Category c").getResultList();

    result.put("categorias", categorias);

    return Optional.of(result)
        .map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());


  }

}
