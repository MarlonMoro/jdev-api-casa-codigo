package br.com.jdev.apicasacodigo.model;

import br.com.jdev.apicasacodigo.dto.AutorRequest;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
  @Column(name = "name", length = 255, nullable = false)
  private String name;
  @Column(name = "email", length = 255, nullable = false)
  private String email;
  @Column(name = "description", length = 400, nullable = false)
  private String description;
  @Column(name = "created_time", nullable = false)
  private LocalDateTime createdTime = LocalDateTime.now();

  @Deprecated
  public Autor() {
  }

  public Autor(String name, String email, String description) {
    this.name = name;
    this.email = email;
    this.description = description;
  }

}
