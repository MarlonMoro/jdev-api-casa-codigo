package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.model.Category;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.util.ExistsId;
import br.com.jdev.apicasacodigo.util.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.springframework.util.Assert;
@Getter
public class NovoLivroRequest {

  @NotBlank
  @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
  private String titulo;
  @NotBlank
  @Size(max = 500)
  private String resumo;
  @NotBlank
  private String sumario;
  @NotNull
  @Min(20)
  private BigDecimal preco;
  @Min(100)
  private int numeroPaginas;
  @NotBlank
  @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
  private String isbn;
  @NotNull
  @Future
  @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
  private LocalDate dataPublicacao;
  @NotNull
  @ExistsId(domainClass = Category.class, fieldName = "id")
  private Long idCategoria;
  @NotNull
  @ExistsId(domainClass = Autor.class, fieldName = "id")
  private Long idAutor;

  public NovoLivroRequest() {
  }

  public NovoLivroRequest(@NotBlank String titulo,
      @NotBlank @Size(max = 500) String resumo,
      @NotBlank String sumario,
      @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas,
      @NotBlank String isbn,
      @NotNull @Future LocalDate dataPublicacao,
      @NotNull Long idCategoria, @NotNull Long idAutor) {
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.numeroPaginas = numeroPaginas;
    this.isbn = isbn;
    this.dataPublicacao = dataPublicacao;
    this.idCategoria = idCategoria;
    this.idAutor = idAutor;
  }

  public Livro toModel(EntityManager manager) {
    Autor autor = manager.find(Autor.class, this.idAutor);
    Category categoria = manager.find(Category.class, this.idCategoria);

    Assert.state(autor != null,
        "Voce esta querendo cadastar um livro com um autor que nao existe no banco id: "
            + this.idAutor);
    Assert.state(categoria != null,
        "Voce esta querendo cadastar um livro com uma categoria que nao existe no banco id: "
            + this.idCategoria);

    return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas,
        this.isbn, this.dataPublicacao, categoria, autor);
  }
}
