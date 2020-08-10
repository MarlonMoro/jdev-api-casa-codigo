package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Book;
import lombok.Getter;

@Getter
public class BookDto {

  private Long id;
  private String title;

  @Deprecated
  public BookDto() {
  }

  private BookDto(Long id, String title){
    this.id = id;
    this.title = title;
  }

  public static BookDto getInstance(Book book) {
    return new BookDto(book.getId(), book.getTitle());
  }
}
