package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Pedido;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PedidoResponse {

  private BigDecimal valorTotal;
  private boolean comDescontoAplicado;
  private BigDecimal valorComDesconto;
  private List<ItemPedidoResponse> itens;

  public PedidoResponse() {
  }

  private PedidoResponse(BigDecimal valorTotal, boolean comDescontoAplicado,
      BigDecimal valorComDesconto,
      List<ItemPedidoResponse> itens) {
    this.valorTotal = valorTotal;
    this.comDescontoAplicado = comDescontoAplicado;
    this.valorComDesconto = valorComDesconto;
    this.itens = itens;
  }

  public static PedidoResponse getInstance(Pedido pedido) {
    List<ItemPedidoResponse> itens = pedido.getItens().stream()
        .map(ItemPedidoResponse::getInstance).collect(Collectors.toList());
    BigDecimal valorComDesconto = pedido.usouCupom() ? pedido.getValorTotalPago() : null;
    return new PedidoResponse(pedido.getTotal(), pedido.usouCupom(), valorComDesconto, itens);
  }

}
