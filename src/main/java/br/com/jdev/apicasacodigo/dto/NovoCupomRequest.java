package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Cupom;
import br.com.jdev.apicasacodigo.util.UniqueValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class NovoCupomRequest {

  @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
  @NotBlank
  private String codigo;
  @Positive
  @NotNull
  private BigDecimal percentualDesconto;
  @Future
  @NotNull
  private LocalDate validade;

  public Cupom toModel(){
    return new Cupom(this.codigo, this.percentualDesconto, this.validade);
  }
}
