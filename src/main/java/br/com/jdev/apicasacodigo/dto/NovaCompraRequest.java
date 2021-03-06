package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.model.Pedido;
import br.com.jdev.apicasacodigo.repository.CupomRepository;
import br.com.jdev.apicasacodigo.util.ExistsId;
import java.util.Optional;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Getter
public class NovaCompraRequest {

  @NotBlank
  private String nome;

  @NotBlank
  private String sobrenome;

  @NotBlank
  private String documento;

  @Email
  @NotBlank
  private String email;

  @Pattern(regexp = "\\d{11}", message = "Digite um número de celular válido")
  @NotBlank
  private String telefone;

  @ExistsId(domainClass = Pais.class, fieldName = "id")
  private Long paisId;

  private Long estadoId;

  @Pattern(regexp = "\\d{8}", message = "Padrão de cep somente números")
  @NotBlank
  private String cep;

  @NotBlank
  private String cidade;

  @NotBlank
  private String endereco;

  private String complemento;

  @Valid
  @NotNull
  private NovoPedidoRequest pedido;

  private String codigoCupom;

  public Optional<String> getCodigoCupom() {
    return Optional.ofNullable(codigoCupom);
  }

  public boolean documentoValido() {
    Assert.hasLength(this.documento, "Um documento não pode ser nulo");

    CPFValidator cpfValidator = new CPFValidator();
    cpfValidator.initialize(null);

    CNPJValidator cnpjValidator = new CNPJValidator();
    cnpjValidator.initialize(null);

    return cpfValidator.isValid(this.documento, null) || cnpjValidator
        .isValid(this.documento, null);
  }

  public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {

    Pais pais = manager.find(Pais.class, paisId);

    Function<Compra, Pedido> funcaoCriaPedido = this.pedido.toModel(manager);

    Compra compra = new Compra(email, nome, sobrenome, documento, cidade, endereco, complemento,
        pais,
        telefone, cep, funcaoCriaPedido);

    Optional.ofNullable(this.estadoId).ifPresent(id ->{
      Estado estado = manager.find(Estado.class, id);
      compra.setEstado(estado);
    });

    if (StringUtils.hasText(this.codigoCupom)) {
      cupomRepository.findByCodigo(this.codigoCupom).ifPresent(compra::aplicaCupom);
    }

    return compra;
  }
}

