package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Category;
import br.com.jdev.apicasacodigo.util.UniqueValue;
import lombok.Getter;

@Getter
public class CategoryRequest {

  @UniqueValue(fieldName = "description", domainClass = Category.class)
  private String description;

  @Deprecated
  public CategoryRequest() {
  }

  public Category toModel(){
    return new Category(this.description);
  }

}
