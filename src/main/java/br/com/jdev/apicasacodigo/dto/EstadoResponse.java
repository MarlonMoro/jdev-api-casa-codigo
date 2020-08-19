package br.com.jdev.apicasacodigo.dto;

public class EstadoResponse {

  private String nome;
  private String pais;

  public EstadoResponse(String nome, String pais) {
    this.nome = nome;
    this.pais = pais;
  }

  public String getNome() {
    return nome;
  }

  public String getPais() {
    return pais;
  }
}
