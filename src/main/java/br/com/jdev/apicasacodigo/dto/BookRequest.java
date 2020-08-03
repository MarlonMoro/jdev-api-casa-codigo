package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.model.Category;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookRequest {

  @NotBlank
  private String title;
  @NotBlank
  private String resume;
  @NotBlank
  private String summary;
  @Min(20)
  @NotNull
  private BigDecimal price;
  @Min(100)
  @NotNull
  private Integer pageNumber;

  private String isbn;
  @Future
  @NotNull
  private LocalDate publicationDate;
  @NotNull
  private String category;
  @NotNull
  private String autor;

}
