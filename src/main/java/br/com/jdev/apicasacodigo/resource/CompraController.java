package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.CompraDto;
import br.com.jdev.apicasacodigo.util.CompraEstadoValidator;
import br.com.jdev.apicasacodigo.util.DocumentoValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  private CompraEstadoValidator compraEstadoValidator;
  @Autowired
  private DocumentoValidator documentoValidator;

  @InitBinder
  public void initBinder(WebDataBinder dataBinder){
    dataBinder.addValidators(compraEstadoValidator, documentoValidator);
  }


  @PostMapping
  public ResponseEntity<?> finalizarCompra(@RequestBody @Valid CompraDto compra){

    System.out.println("teste");



    return ResponseEntity.noContent().build();
  }

}
