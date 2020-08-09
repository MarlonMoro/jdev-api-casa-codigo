package br.com.jdev.apicasacodigo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;
  private String titulo;
  private String resumo;
  private String sumario;
  private BigDecimal preco;
  private @Min(100) int numeroPaginas;
  private String isbn;
  private LocalDate dataPublicacao;
  @ManyToOne
  private Category categoria;
  @ManyToOne
  private Autor autor;

  public Livro() {
  }

  public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
      @NotBlank String sumario, @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas,
      @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull @Valid Category categoria, @NotNull @Valid Autor autor) {
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.numeroPaginas = numeroPaginas;
    this.isbn = isbn;
    this.dataPublicacao = dataPublicacao;
    this.categoria = categoria;
    this.autor = autor;
  }
}
