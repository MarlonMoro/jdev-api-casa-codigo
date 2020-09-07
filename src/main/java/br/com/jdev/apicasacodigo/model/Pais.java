package br.com.jdev.apicasacodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, unique = true)
  private String nome;
  @JsonIgnore
  @OneToMany(mappedBy = "pais")
  private List<Estado> estados;

  @Deprecated
  public Pais() {
  }

  public Pais(String nome) {
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public List<Estado> getEstados() {
    return estados;
  }
}
