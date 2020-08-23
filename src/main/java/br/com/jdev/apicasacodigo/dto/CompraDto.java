package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

public class CompraDto {

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

  @NotBlank
  private String pais;

  private String estado;

  @Pattern(regexp = "\\d{8}", message = "Padrão de cep somente números")
  @NotBlank
  private String cep;

  @NotBlank
  private String cidade;

  @NotBlank
  private String endereco;

  private String complemento;

  @Positive
  @NotNull
  private BigDecimal total;

  @Size(min = 1, message = "Pelo menos um item é obrigatório para efetivar a compra")
  @NotNull
  private List<@Valid ItemRequest> itens;


  public BigDecimal getTotal() {
    return total;
  }

  public List<ItemRequest> getItens() {
    return itens;
  }

  public String getNome() {
    return nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public String getDocumento() {
    return documento;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getPais() {
    return pais;
  }

  public String getEstado() {
    return estado;
  }

  public String getCep() {
    return cep;
  }

  public String getCidade() {
    return cidade;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getComplemento() {
    return complemento;
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
}

