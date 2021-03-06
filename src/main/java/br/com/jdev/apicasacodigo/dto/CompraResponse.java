package br.com.jdev.apicasacodigo.dto;

import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.model.Estado;
import java.util.Optional;
import lombok.Getter;

@Getter
public class CompraResponse {

  private Long id;

  private String nome;

  private String sobrenome;

  private String documento;

  private String email;

  private String telefone;

  private String pais;

  private String estado;

  private String cep;

  private String cidade;

  private String endereco;

  private String complemento;

  private PedidoResponse pedido;

  public CompraResponse() {
  }

  private CompraResponse(Long id, String nome, String sobrenome, String documento,
      String email, String telefone, String pais, String estado, String cep, String cidade,
      String endereco, String complemento, PedidoResponse pedido) {
    this.id = id;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.documento = documento;
    this.email = email;
    this.telefone = telefone;
    this.pais = pais;
    this.estado = estado;
    this.cep = cep;
    this.cidade = cidade;
    this.endereco = endereco;
    this.complemento = complemento;
    this.pedido = pedido;
  }

  public static CompraResponse getInstance(Compra compra) {
    PedidoResponse pedidoResponse = PedidoResponse.getInstance(compra.getPedido());
    String estado = Optional.ofNullable(compra.getEstado()).map(Estado::getNome).orElse(null);
    return new CompraResponse(compra.getId(), compra.getNome(), compra.getSobrenome(),
        compra.getDocumento(), compra.getEmail(), compra.getTelefone(), compra.getPais().getNome(),
        estado,
        compra.getCep(), compra.getCidade(), compra.getEndereco(), compra.getComplemento(),
        pedidoResponse);
  }


}
