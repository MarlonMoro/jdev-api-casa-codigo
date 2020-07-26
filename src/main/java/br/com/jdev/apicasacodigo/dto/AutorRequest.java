package br.com.jdev.apicasacodigo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AutorRequest {

  @NotBlank
  private String name;
  @Email
  @NotBlank
  private String email;
  @NotBlank
  @Size(max = 400)
  private String description;

  @Deprecated
  public AutorRequest() {
  }
}
