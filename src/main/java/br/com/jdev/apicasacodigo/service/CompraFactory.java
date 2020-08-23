package br.com.jdev.apicasacodigo.service;

import br.com.jdev.apicasacodigo.dto.CompraDto;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.model.Estado;
import br.com.jdev.apicasacodigo.model.Pais;
import br.com.jdev.apicasacodigo.repository.EstadoRepository;
import br.com.jdev.apicasacodigo.repository.PaisRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class CompraFactory {

  @Autowired
  private PaisRepository paisRepository;
  @Autowired
  private EstadoRepository estadoRepository;
  @PersistenceContext
  private EntityManager manager;

  @Transactional
  public Compra build(CompraDto compraDto) throws BusinessException {

    Pais pais = paisRepository.findByNome(compraDto.getPais())
        .orElseThrow(() -> new BusinessException(
            HttpStatus.BAD_REQUEST, "Pais informado invalido",
            String.format("O pais %s nao existe", compraDto.getPais())));

    Compra compra = new Compra(compraDto.getEmail(), compraDto.getTelefone(), compraDto.getNome(), compraDto.getSobrenome(),
        compraDto.getDocumento(),
        compraDto.getEndereco(), compraDto.getCep(), compraDto.getComplemento(), pais,
        compraDto.getCidade(),
        compraDto.getTotal());

    if (!ObjectUtils.isEmpty(compraDto.getEstado())) {
      Estado estado = estadoRepository.findByPaisAndNome(pais, compraDto.getEstado())
          .orElseThrow(
              () -> new BusinessException(HttpStatus.BAD_REQUEST, "Estado invalido", String
                  .format("O estado %s nao pertence ao pais %s", compraDto.getEstado(),
                      compraDto.getPais())));
      compra.setEstado(estado);
    }
    manager.persist(compra);
    return compra;
  }

}
