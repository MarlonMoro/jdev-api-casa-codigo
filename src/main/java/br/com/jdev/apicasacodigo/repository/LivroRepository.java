package br.com.jdev.apicasacodigo.repository;

import br.com.jdev.apicasacodigo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
