package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.AutorRequest;
import br.com.jdev.apicasacodigo.dto.AutorResponse;
import br.com.jdev.apicasacodigo.service.AutorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/autor")
public class AutorController {


  @Autowired
  private AutorService autorService;

  @PostMapping
  public ResponseEntity<?> createAutor(@RequestBody @Valid AutorRequest request) {

    return ResponseEntity.ok(AutorResponse.getInstance(autorService.createAutor(request)));
  }

}
