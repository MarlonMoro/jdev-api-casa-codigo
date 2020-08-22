package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.CompraDto;
import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.repository.PaisRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompraEstadoValidator implements Validator {

  @Autowired
  private PaisRepository paisRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return CompraDto.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CompraDto compraDto = (CompraDto) o;

    Optional<Pais> paisOpt = paisRepository.findByNome(compraDto.getPais());

    if (!paisOpt.isPresent()) {
      errors.rejectValue("pais", null,
          String.format("Pais informado não existe %s", compraDto.getPais()));
    }

    paisOpt.ifPresent(pais -> {

      if (pais.getEstados().isEmpty()) {
        return;
      }

      if (ObjectUtils.isEmpty(compraDto.getEstado())) {
        errors.rejectValue("estado", null,
            String.format("Para o pais %s é obrigatorio um estado.",
                compraDto.getPais()));
      }

      if (pais.getEstados().stream().noneMatch(estado -> estado.getNome().equalsIgnoreCase(
          compraDto.getEstado()))) {
        errors.rejectValue("estado", null,
            String.format("O estado %s não pertence ao pais informado %s",
                compraDto.getEstado(), compraDto.getPais()));
      }
    });


  }
}
