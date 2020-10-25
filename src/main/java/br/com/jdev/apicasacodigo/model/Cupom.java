package br.com.jdev.apicasacodigo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cupom")
@Entity
@Getter
@NoArgsConstructor
public class Cupom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "codigo")
  private String codigo;
  @Column(name = "percentual_desconto")
  private BigDecimal percentualDesconto;
  @Column(name = "validade")
  private LocalDate validade;

  public Cupom(String codigo, BigDecimal percentualDesconto, LocalDate validade) {
    this.codigo = codigo;
    this.percentualDesconto = percentualDesconto;
    this.validade = validade;
  }
}
