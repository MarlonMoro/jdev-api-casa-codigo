package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.NovoCupomRequest;
import br.com.jdev.apicasacodigo.model.Cupom;
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
@RequestMapping(value = "/cupons")
public class NovoCupomController {

  @PersistenceContext
  private EntityManager manager;

  @Transactional
  @PostMapping
  public ResponseEntity<?> cria(@RequestBody @Valid NovoCupomRequest request){

    Cupom cupom = request.toModel();

    manager.persist(cupom);

    return ResponseEntity.ok(cupom);
  }

}
