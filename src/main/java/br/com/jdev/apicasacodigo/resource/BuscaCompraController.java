package br.com.jdev.apicasacodigo.resource;


import br.com.jdev.apicasacodigo.dto.CompraResponse;
import br.com.jdev.apicasacodigo.exceptions.NotFoundException;
import br.com.jdev.apicasacodigo.model.Compra;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/compra")
@RestController
public class BuscaCompraController {

  @PersistenceContext
  private EntityManager manager;

  @GetMapping(path = "/{id}")
  public ResponseEntity<CompraResponse> findCompra(@PathVariable(name = "id") Long id)
      throws NotFoundException {

    Compra compra = Optional.ofNullable(manager.find(Compra.class, id))
        .orElseThrow(() -> new NotFoundException("Id informado não encontrado"));

    return ResponseEntity.ok(CompraResponse.getInstance(compra));
  }
}
