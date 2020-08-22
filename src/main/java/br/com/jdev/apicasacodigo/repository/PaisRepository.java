package br.com.jdev.apicasacodigo.repository;

import br.com.jdev.apicasacodigo.model.Pais;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {

  Optional<Pais> findByNome(String nome);

}
