package br.com.jdev.apicasacodigo.repository;

import br.com.jdev.apicasacodigo.model.Cupom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, Long> {


  Optional<Cupom> findByCodigo(String codigo);

}
