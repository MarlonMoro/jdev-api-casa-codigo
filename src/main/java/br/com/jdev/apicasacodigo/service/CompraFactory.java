package br.com.jdev.apicasacodigo.service;

import br.com.jdev.apicasacodigo.dto.NovaCompraRequest;
import br.com.jdev.apicasacodigo.exceptions.BusinessException;
import br.com.jdev.apicasacodigo.model.Compra;
import br.com.jdev.apicasacodigo.repository.EstadoRepository;
import br.com.jdev.apicasacodigo.repository.PaisRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraFactory {

  @Autowired
  private PaisRepository paisRepository;
  @Autowired
  private EstadoRepository estadoRepository;
  @PersistenceContext
  private EntityManager manager;

  @Transactional
  public Compra build(NovaCompraRequest novaCompraRequest) throws BusinessException {


    return null;
  }

}
