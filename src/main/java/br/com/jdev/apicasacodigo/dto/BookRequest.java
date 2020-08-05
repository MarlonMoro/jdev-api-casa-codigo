package br.com.jdev.apicasacodigo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
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
