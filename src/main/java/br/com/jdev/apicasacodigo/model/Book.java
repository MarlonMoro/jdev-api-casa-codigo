package br.com.jdev.apicasacodigo.model;

import br.com.jdev.apicasacodigo.dto.BookRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "title", nullable = false, unique = true)
  private String title;
  @Column(name = "resume", nullable = false, length = 500)
  private String resume;
  @Column(name = "summary", nullable = false)
  private String summary;
  @Min(20)
  @Column(name = "price", nullable = false)
  private BigDecimal price;
  @Min(100)
  @Column(name = "pageNumber", nullable = false)
  private Integer pageNumber;
  @Column(name = "isbn", nullable = false, unique = true)
  private String isbn;
  @Future
  @Column(name = "publication_date", nullable = false)
  private LocalDate publicationDate;
  @NotNull
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  @NotNull
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "autor_id")
  private Autor autor;

  @Deprecated
  public Book() {
  }

  private Book(String title, String resume, String summary,
      @Min(20) BigDecimal price, @Min(100) Integer pageNumber, String isbn,
      @Future LocalDate publicationDate,
      @NotNull Category category,
      @NotNull Autor autor) {
    this.title = title;
    this.resume = resume;
    this.summary = summary;
    this.price = price;
    this.pageNumber = pageNumber;
    this.isbn = isbn;
    this.publicationDate = publicationDate;
    this.category = category;
    this.autor = autor;
  }

  public static Book getInstance(BookRequest bookRequest, Category category, Autor autor) {
    return new Book(bookRequest.getTitle(), bookRequest.getResume(), bookRequest.getSummary(),
        bookRequest.getPrice(), bookRequest.getPageNumber(), bookRequest.getIsbn(),
        bookRequest.getPublicationDate(), category, autor);
  }
}
