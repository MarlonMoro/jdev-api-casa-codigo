package br.com.jdev.apicasacodigo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

