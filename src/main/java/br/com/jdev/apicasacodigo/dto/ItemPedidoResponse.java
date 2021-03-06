package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.ItemPedido;
import lombok.Getter;

@Getter
public class ItemPedidoResponse {

  private int quatidade;
  private String titulo;
  private String categoria;

  public ItemPedidoResponse() {
  }

  private ItemPedidoResponse(int quatidade, String titulo, String categoria) {
    this.quatidade = quatidade;
    this.titulo = titulo;
    this.categoria = categoria;
  }

  public static ItemPedidoResponse getInstance(ItemPedido itemPedido) {
    return new ItemPedidoResponse(itemPedido.getQuantidade(), itemPedido.getItem().getTitulo(),
        itemPedido.getItem().getCategoria().getDescription());
  }

}
