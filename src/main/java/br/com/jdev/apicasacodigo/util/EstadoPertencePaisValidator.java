package br.com.jdev.apicasacodigo.util;

import br.com.jdev.apicasacodigo.dto.NovaCompraRequest;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertencePaisValidator implements Validator {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public boolean supports(Class<?> aClass) {
    return NovaCompraRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;

    Pais pais = manager.find(Pais.class, novaCompraRequest.getPaisId());

    if (ObjectUtils.isEmpty(pais)) {
      errors.rejectValue("paisId", null,
          String.format("O id do pais informado não existe %d", novaCompraRequest.getPaisId()));
    }


    if (pais.getEstados().isEmpty()) {
      return;
    }


    if (ObjectUtils.isEmpty(novaCompraRequest.getEstadoId())) {
      errors.rejectValue("estadoId", null,
          String.format("Para o pais %s, com id %d é obrigatório informar um estado", pais.getNome(), pais.getId()));
      return;
    }


    Estado estado = manager.find(Estado.class, novaCompraRequest.getEstadoId());

    if (ObjectUtils.isEmpty(estado)) {
      errors.rejectValue("estadoId", null,
          String.format("O Estado com id %d não existe", novaCompraRequest.getEstadoId()));
      return;
    }

    if(!pais.getEstados().contains(estado)){
      errors.rejectValue("estadoId", null,
          String.format("O estado informado estadoId: %d com não pertence ao pais informado %s",
              novaCompraRequest.getEstadoId(), novaCompraRequest.getPaisId()));
    }

  }
}
