package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Category;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryRequest {

  @NotBlank
  private String name;

  @Deprecated
  public CategoryRequest() {
  }

  public Category toModel(){
    return new Category(this.name);
  }
}
