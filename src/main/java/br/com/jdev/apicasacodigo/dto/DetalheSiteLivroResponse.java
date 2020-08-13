package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.model.Livro;
import java.math.BigDecimal;

public class DetalheSiteLivroResponse {


  private String titulo;
  private Autor Autor;
  private String resumo;
  private BigDecimal preco;
  private String sumario;
  private String isbn;
  private int numeroPaginas;

  @Deprecated
  public DetalheSiteLivroResponse() {
  }

  public DetalheSiteLivroResponse(Livro livro) {
    this.titulo = livro.getTitulo();
    this.Autor = livro.getAutor();
    this.resumo = livro.getResumo();
    this.preco = livro.getPreco();
    this.sumario = livro.getSumario();
    this.isbn = livro.getIsbn();
    this.numeroPaginas = livro.getNumeroPaginas();
  }

  public String getTitulo() {
    return titulo;
  }

  public br.com.jdev.apicasacodigo.model.Autor getAutor() {
    return Autor;
  }

  public String getResumo() {
    return resumo;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public String getSumario() {
    return sumario;
  }

  public String getIsbn() {
    return isbn;
  }

  public int getNumeroPaginas() {
    return numeroPaginas;
  }
}

