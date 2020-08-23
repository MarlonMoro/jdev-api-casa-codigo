package br.com.jdev.apicasacodigo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemRequest {

  @NotNull
  private Long id;
  @Positive
  @NotNull
  private Integer quantidade;

  public Long getId() {
    return id;
  }

  public Integer getQuantidade() {
    return quantidade;
  }
}
