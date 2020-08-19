package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.util.UniqueValue;
import javax.validation.constraints.NotBlank;

public class PaisRequest {

  @UniqueValue(domainClass = Pais.class, fieldName = "nome")
  @NotBlank
  private String nome;

  public PaisRequest() {
  }

  public String getNome() {
    return nome;
  }

  public Pais toModel(){
    return new Pais(this.nome);
  }
}
