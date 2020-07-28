package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.resource.validators.UniqueEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AutorRequest {

  @NotBlank
  private String name;
  @UniqueEmail
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
