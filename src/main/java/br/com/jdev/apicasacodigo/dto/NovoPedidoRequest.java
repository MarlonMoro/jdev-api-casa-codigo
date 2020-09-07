package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.model.ItemPedido;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.model.Pedido;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class NovoPedidoRequest {

  @Positive
  @NotNull
  private BigDecimal total;

  @Size(min = 1, message = "Pelo menos um item é obrigatório para efetivar a compra")
  @NotNull
  private List<@Valid ItemRequest> itens = new ArrayList<>();

  public NovoPedidoRequest() {
  }

  public Function<Compra, Pedido> toModel(EntityManager manager) {

    Set<ItemPedido> itensPedido = itens.stream().map(itemRequest -> itemRequest.toModel(manager))
        .collect(Collectors.toSet());

    return (compra) -> {
      Pedido pedido = new Pedido(compra, itensPedido);
      Assert.isTrue(pedido.totalIgual(this.total),
          "O total enviado tem que ser igual ao total calculado");
      return pedido;
    };

  }
}
