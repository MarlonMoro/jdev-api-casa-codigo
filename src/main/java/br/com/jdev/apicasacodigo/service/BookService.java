package br.com.jdev.apicasacodigo.service;

import br.com.jdev.apicasacodigo.dto.BookRequest;
import br.com.jdev.apicasacodigo.exceptions.NotFoundException;
import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.model.Book;
import br.com.jdev.apicasacodigo.model.Category;
import br.com.jdev.apicasacodigo.repository.AutorRepository;
import br.com.jdev.apicasacodigo.repository.BookRepository;
import br.com.jdev.apicasacodigo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private AutorRepository autorRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private BookRepository bookRepository;

  public Book generateBook(BookRequest bookRequest) {

    Autor autor = autorRepository.findByName(bookRequest.getAutor())
        .orElseThrow(() -> new NotFoundException("Autor not found", HttpStatus.BAD_REQUEST));

    Category category = categoryRepository.findByName(bookRequest.getCategory())
        .orElseThrow(() -> new NotFoundException("Category not found", HttpStatus.BAD_REQUEST));

    return bookRepository.save(Book.getInstance(bookRequest, category, autor));


  }

}
