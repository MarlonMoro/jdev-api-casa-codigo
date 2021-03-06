package br.com.jdev.apicasacodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Entity
@Getter
@Table(name = "pedido")
public class Pedido {

  @Id
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @OneToOne
  @MapsId
  private Compra compra;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  @ElementCollection
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido() {
  }

  public Pedido(Compra compra, Set<ItemPedido> itensPedido) {
    Assert.isTrue(!itensPedido.isEmpty(), "Itens sao exigidos em um pedido");
    this.compra = compra;
    this.itens.addAll(itensPedido);
    this.total = itensPedido.stream().map(ItemPedido::total)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public boolean totalIgual(BigDecimal total){
    return this.total.compareTo(total) == 0;
  }

  public boolean usouCupom(){
    return !ObjectUtils.isEmpty(this.compra.getCupomAplicado());
  }

  public BigDecimal getValorTotalPago() {
    return Optional.ofNullable(this.compra.getCupomAplicado())
        .map(CupomAplicado::getCupom)
        .map(Cupom::getPercentualDesconto)
        .map(percentualDesconto -> this.total.subtract(this.total.multiply(percentualDesconto))
            .setScale(2, RoundingMode.HALF_EVEN))
        .orElse(this.total);
  }

}
