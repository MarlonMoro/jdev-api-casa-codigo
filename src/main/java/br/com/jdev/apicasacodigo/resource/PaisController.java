package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.EstadoResponse;
import br.com.jdev.apicasacodigo.dto.PaisRequest;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import br.com.jdev.apicasacodigo.exceptions.NotFoundException;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.service.EstadoService;
import br.com.jdev.apicasacodigo.util.ExistsId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/paises")
@RestController
public class PaisController {

  @PersistenceContext
  private EntityManager manager;
  @Autowired
  private EstadoService estadoService;

  @PostMapping
  @Transactional
  public ResponseEntity<Pais> salvarPais(@RequestBody @Valid PaisRequest paisRequest) {
    Pais pais = paisRequest.toModel();
    manager.persist(pais);
    return ResponseEntity.ok(pais);
  }


  @PostMapping(path = "/{paisId:\\d}/estados")
  public ResponseEntity<?> criarEstadosDeUmPais(
      @PathVariable(name = "paisId") Long paisId,
      @RequestBody @NotNull List<String> estados)
      throws NotFoundException, BusinessException {

    Pais pais = Optional.ofNullable(manager.find(Pais.class, paisId))
        .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Pais informado não existe", String.format("id: %s", paisId)));

    List<EstadoResponse> estadosSalvos = estadoService.salvarNovosEstados(pais, estados);

    return estadosSalvos.isEmpty() ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(estadosSalvos);

  }


}
