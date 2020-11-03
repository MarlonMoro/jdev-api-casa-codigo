package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.NovaCompraRequest;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.repository.CupomRepository;
import br.com.jdev.apicasacodigo.service.CompraFactory;
import br.com.jdev.apicasacodigo.util.DocumentoValidator;
import br.com.jdev.apicasacodigo.util.EstadoPertencePaisValidator;
import br.com.jdev.apicasacodigo.util.ValorTotalCompraValidator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/compra")
@RestController
public class CompraController {

  @Autowired
  private EstadoPertencePaisValidator estadoPertencePaisValidator;
  @Autowired
  private DocumentoValidator documentoValidator;
  @Autowired
  private ValorTotalCompraValidator valorTotalCompraValidator;
  @Autowired
  private CupomRepository cupomRepository;

  @PersistenceContext
  private EntityManager entityManager;


  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder
        .addValidators(estadoPertencePaisValidator, documentoValidator, valorTotalCompraValidator);
  }


  @PostMapping
  @Transactional
  public ResponseEntity<?> finalizarCompra(@RequestBody @Valid NovaCompraRequest compraRequest)
      throws BusinessException {

    System.out.println("teste");

    Compra compra = compraRequest.toModel(entityManager, cupomRepository);

    entityManager.persist(compra);

    return ResponseEntity.ok(compra);
  }

}
