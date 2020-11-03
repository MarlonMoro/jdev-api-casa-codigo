package br.com.jdev.apicasacodigo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Embeddable
public class CupomAplicado {

  private BigDecimal percentualDescontoMomento;
  private LocalDate validadeMomento;
  @ManyToOne
  private Cupom cupom;

  public CupomAplicado() {
  }

  public CupomAplicado(Cupom cupom) {
    this.cupom = cupom;
    this.percentualDescontoMomento = cupom.getPercentualDesconto();
    this.validadeMomento = cupom.getValidade();
  }
}
