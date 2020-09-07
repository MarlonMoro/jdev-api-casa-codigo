package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.ItemPedido;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.util.ExistsId;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ItemRequest {

  @ExistsId(domainClass = Livro.class, fieldName = "id")
  @NotNull
  private Long id;
  @Positive
  @NotNull
  private Integer quantidade;


  public ItemPedido toModel(EntityManager manager) {

    Livro livro = manager.find(Livro.class, id);

    return new ItemPedido(quantidade, livro);

  }
}
