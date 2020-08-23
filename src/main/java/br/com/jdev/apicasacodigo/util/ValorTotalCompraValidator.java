package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.CompraDto;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.repository.LivroRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValorTotalCompraValidator implements Validator {

  @Autowired
  private LivroRepository livroRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return CompraDto.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    CompraDto compraDto = (CompraDto) o;

    Map<Integer, Livro> quantidadeLivro = new HashMap<>();

    compraDto.getItens().forEach(livro -> {
      Optional<Livro> foundedLivro = livroRepository.findById(livro.getId());
      if (foundedLivro.isPresent()) {
        quantidadeLivro.put(livro.getQuantidade(), foundedLivro.get());
      } else {
        errors.rejectValue("itens", null,
            String.format("O livro com id %d não existe, informe um livro válido", livro.getId()));
      }
    });

    quantidadeLivro.entrySet().stream()
        .map(entry -> entry.getValue().getPreco().multiply(BigDecimal.valueOf(entry.getKey())))
        .reduce(BigDecimal::add).ifPresent(totalcalculado -> {
      if (compraDto.getTotal().compareTo(totalcalculado) != 0) {
        errors.rejectValue("total", null, "Valor total inválido!");
      }
    });

  }
}
