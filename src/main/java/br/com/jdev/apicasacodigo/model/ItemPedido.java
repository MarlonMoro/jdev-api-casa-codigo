package br.com.jdev.apicasacodigo.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Embeddable
public class ItemPedido {

  @Column(name = "quantidade")
  private int quantidade;

  @Column(name = "preco_momento")
  private BigDecimal precoMomento;

  @ManyToOne
  private Livro item;

  public ItemPedido() {
  }

  public ItemPedido(int quantidade,
      Livro livro) {
    this.quantidade = quantidade;
    this.item = livro;
    this.precoMomento = livro.getPreco();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPedido that = (ItemPedido) o;
    return item.equals(that.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item);
  }

  public BigDecimal total(){
    return precoMomento.multiply(BigDecimal.valueOf(this.quantidade));
  }
}
