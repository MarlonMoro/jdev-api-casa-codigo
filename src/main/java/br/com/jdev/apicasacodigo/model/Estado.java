package br.com.jdev.apicasacodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, unique = true)
  private String nome;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "pais_id", nullable = false)
  private Pais pais;


  public Estado() {
  }

  public Estado(String nome, Pais pais) {
    this.nome = nome;
    this.pais = pais;
  }

  public String getNome() {
    return nome;
  }

  public Pais getPais() {
    return pais;
  }

  public boolean estadoPercente(Pais pais){
    return this.pais.getNome().equalsIgnoreCase(pais.getNome());
  }
}
