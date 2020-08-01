package br.com.jdev.apicasacodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
@Table(name = "category")
public class Category {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Column(name = "NAME", nullable = false)
  private String name;

  @Deprecated
  public Category() {
  }

  public Category(@NotBlank String name) {
    this.name = name;
  }


}
