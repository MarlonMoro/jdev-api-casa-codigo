package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.NovaCompraRequest;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.repository.LivroRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValorTotalCompraValidator implements Validator {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public boolean supports(Class<?> aClass) {
    return NovaCompraRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;

    Map<Integer, BigDecimal> quantidadeLivro = new HashMap<>();

    novaCompraRequest.getPedido().getItens().forEach(itemRequest -> {
      Livro livro = manager.find(Livro.class, itemRequest.getId());
      if(livro == null){
        errors.rejectValue("pedido", null, String.format("Item com id %d informado é inválido", itemRequest.getId()));
      } else {
        quantidadeLivro.put(itemRequest.getQuantidade(), livro.getPreco());
      }

    });
    if(errors.hasErrors())
      return;

    Optional<BigDecimal> optTotal = quantidadeLivro.entrySet().stream()
        .map(entry -> BigDecimal.valueOf(entry.getKey()).multiply(entry.getValue()))
        .reduce(BigDecimal::add);

    if(optTotal.isPresent()){
      if(novaCompraRequest.getPedido().getTotal().compareTo(optTotal.get()) != 0){
        errors.rejectValue("pedido", null, "Total informado invalido");
      }
    } else {
      errors.rejectValue("itens", null, "Erro ao validar total de itens");
    }

  }
}
