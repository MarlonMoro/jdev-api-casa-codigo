package br.com.jdev.apicasacodigo.service;

import br.com.jdev.apicasacodigo.dto.EstadoResponse;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.repository.EstadoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

  @Autowired
  private EstadoRepository estadoRepository;

  public List<EstadoResponse> salvarNovosEstados(Pais pais, List<String> nomesEstados)
      throws BusinessException {

    validate(pais, nomesEstados);

    List<EstadoResponse> estadosSalvos = new ArrayList<>();
    nomesEstados.forEach(nomeEstado -> {
      Estado estado = estadoRepository.save(new Estado(nomeEstado, pais));
      estadosSalvos.add(new EstadoResponse(estado.getNome(), estado.getPais().getNome()));
    });

    return estadosSalvos;

  }

  private void validate(Pais pais, List<String> nomesEstados) throws BusinessException {
    List<String> estadosInvalidos = new ArrayList<>();
    nomesEstados.forEach(nomeEstado -> estadoRepository.findByPaisAndNome(pais, nomeEstado)
        .ifPresent(estado -> estadosInvalidos.add(estado.getNome())));

    if(!estadosInvalidos.isEmpty()){
      throw new BusinessException(HttpStatus.BAD_REQUEST, "Paises já cadastrados", estadosInvalidos);
    }
  }

}
