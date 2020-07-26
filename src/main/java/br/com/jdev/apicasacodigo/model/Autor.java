package br.com.jdev.apicasacodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  private String description;

  @Deprecated
  public Autor() {
  }

  public Autor(String name, String email, String description) {
    this.name = name;
    this.email = email;
    this.description = description;
  }
}
