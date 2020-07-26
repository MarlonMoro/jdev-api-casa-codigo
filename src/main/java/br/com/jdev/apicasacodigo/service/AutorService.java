package br.com.jdev.apicasacodigo.service;

import br.com.jdev.apicasacodigo.dto.AutorRequest;
import br.com.jdev.apicasacodigo.exceptions.ServerException;
import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.repository.AutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutorService {

  @Autowired
  private AutorRepository autorRepository;

  public Autor createAutor(AutorRequest autorRequest) {
    try {
      return autorRepository.save(Autor.getInstance(autorRequest));
    } catch (Exception e){
      log.error("Error on create a Autor {}", e.getLocalizedMessage());
      throw new ServerException();
    }

  }
}
