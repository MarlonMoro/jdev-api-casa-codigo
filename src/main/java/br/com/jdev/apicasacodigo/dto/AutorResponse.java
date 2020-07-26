package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Autor;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AutorResponse {

  private String name;
  private String email;
  private String description;
  private LocalDateTime createdTime;

  public AutorResponse() {
  }

  private AutorResponse(String name, String email, String description,
      LocalDateTime createdTime) {
    this.name = name;
    this.email = email;
    this.description = description;
    this.createdTime = createdTime;
  }

  public static AutorResponse getInstance(Autor autor) {
    return new AutorResponse(autor.getName(), autor.getEmail(), autor.getDescription(),
        autor.getCreatedTime());
  }
}
