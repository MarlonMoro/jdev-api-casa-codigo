package br.com.jdev.apicasacodigo.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "compra")
public class Compra {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "nome", nullable = false)
  private String nome;
  @Column(name = "sobrenome", nullable = false)
  private String sobrenome;
  @Column(name = "documento", nullable = false)
  private String documento;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "telefone", nullable = false)
  private String telefone;
  @ManyToOne
  @JoinColumn(name = "pais_id")
  private Pais pais;
  @ManyToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;
  @Column(name = "cep", nullable = false)
  private String cep;
  @Column(name = "cidade", nullable = false)
  private String cidade;
  @Column(name = "endereco", nullable = false)
  private String endereco;
  @Column(name = "complemento")
  private String complemento;
  @Column(name = "total", nullable = false)
  private BigDecimal total;


  public Compra(@Email @NotBlank String email, @NotBlank String telefone, @NotBlank String nome, @NotBlank String sobrenome,
      @NotBlank String documento, @NotBlank String endereco, @NotBlank String cep,
      String complemento, Pais pais, @NotBlank String cidade, @Positive @NotNull BigDecimal total) {

    this.email = email;
    this.telefone = telefone;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.documento = documento;
    this.endereco = endereco;
    this.cep = cep;
    this.complemento = complemento;
    this.pais = pais;
    this.cidade = cidade;
    this.total = total;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public String getDocumento() {
    return documento;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefone() {
    return telefone;
  }

  public Pais getPais() {
    return pais;
  }

  public Estado getEstado() {
    return estado;
  }

  public String getCep() {
    return cep;
  }

  public String getCidade() {
    return cidade;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getComplemento() {
    return complemento;
  }

  public BigDecimal getTotal() {
    return total;
  }
}
