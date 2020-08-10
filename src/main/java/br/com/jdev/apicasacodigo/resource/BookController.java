package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.BookDto;
import br.com.jdev.apicasacodigo.dto.BookRequest;
import br.com.jdev.apicasacodigo.model.Book;
import br.com.jdev.apicasacodigo.repository.BookRepository;
import br.com.jdev.apicasacodigo.service.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
  @Autowired
  private BookRepository bookRepository;

  @PostMapping
  public ResponseEntity<?> generateBook(@RequestBody @Valid BookRequest bookRequest) {

    return ResponseEntity.created(null).body(bookService.generateBook(bookRequest));
  }


  public ResponseEntity<?> getAllBooks() {

    List<BookDto> books = bookRepository.findAll().stream().map(BookDto::getInstance)
        .collect(Collectors.toList());

    return Optional.of(books).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());

  }

}
