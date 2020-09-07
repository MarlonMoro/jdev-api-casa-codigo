package br.com.jdev.apicasacodigo.model;

import java.util.function.Function;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.util.Assert;

@Entity
@Table(name = "compra")
@Getter
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

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "compra")
  private Pedido pedido;

  public Compra() {
  }

  public Compra(String email, String nome, String sobrenome, String documento, String cidade, String endereco,
      String complemento, Pais pais, String telefone, String cep, Function<Compra, Pedido> funcaoCriaPedido) {
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.documento = documento;
    this.email = email;
    this.telefone = telefone;
    this.pais = pais;
    this.cep = cep;
    this.cidade = cidade;
    this.endereco = endereco;
    this.complemento = complemento;
    this.pedido = funcaoCriaPedido.apply(this);

  }


  public void setEstado(@NotNull @Valid Estado estado) {
    Assert.notNull(this.pais, "Para associar um estado, primeiro deve existir um pais!");
    Assert.isTrue(estado.estadoPercente(this.pais), "Estado tem que ser do mesmo pais");
    this.estado = estado;
  }

}
