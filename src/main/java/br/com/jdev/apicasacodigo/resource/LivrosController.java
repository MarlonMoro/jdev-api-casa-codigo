package br.com.jdev.apicasacodigo.resource;

import br.com.jdev.apicasacodigo.dto.NovoLivroRequest;
import br.com.jdev.apicasacodigo.model.Livro;
import br.com.jdev.apicasacodigo.repository.LivroRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/livros")
public class LivrosController {


  @PersistenceContext
  private EntityManager manager;
  @Autowired
  private LivroRepository livroRepository;

  @Transactional
  @PostMapping()
  public ResponseEntity<?> crairLivro(@RequestBody @Valid NovoLivroRequest livroRequest) {

    Livro livro = livroRequest.toModel(manager);
    manager.persist(livro);
    return ResponseEntity.ok(livro);
  }

  @GetMapping
  public ResponseEntity<?> listarTodosLivros() {
    List<Livro> livros = livroRepository.findAll();

    return livros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(livros);
  }

}
