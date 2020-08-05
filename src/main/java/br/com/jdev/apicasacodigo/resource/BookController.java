package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.BookRequest;
import br.com.jdev.apicasacodigo.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping
  public ResponseEntity<?> generateBook(@RequestBody @Valid BookRequest bookRequest) {

    return ResponseEntity.created(null).body(bookService.generateBook(bookRequest));
  }

}
