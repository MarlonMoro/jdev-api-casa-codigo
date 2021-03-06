package br.com.jdev.apicasacodigo;

import br.com.jdev.apicasacodigo.dto.NovoLivroRequest;
import br.com.jdev.apicasacodigo.model.Autor;
import br.com.jdev.apicasacodigo.model.Category;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NovoLivroRequestTest {

  private NovoLivroRequest request = new NovoLivroRequest("titulo", "resumo", "sumario",
      BigDecimal.ZERO, 10, "isbn",
      LocalDate.of(2020, 3, 3), 1L, 2L);

  @Test
  @DisplayName("Cria um livro com sucesso a partir do livro request")
  public void criaLivroComSucesso() {

    EntityManager manager = Mockito.mock(EntityManager.class);

    Mockito.when(manager.find(Autor.class, 2L)).thenReturn(new Autor("", "", ""));
    Mockito.when(manager.find(Category.class, 1L)).thenReturn(new Category(""));

    Assertions.assertNotNull(request.toModel(manager));

  }

  @Test
  @DisplayName("Estoura exception IllegalState para Autor não encontrado")
  public void estouraExceptionParaAutorNulo(){

    EntityManager manager = Mockito.mock(EntityManager.class);

    Mockito.when(manager.find(Autor.class, 2L)).thenReturn(null);
    Mockito.when(manager.find(Category.class, 1L)).thenReturn(new Category(""));

    Assertions.assertThrows(IllegalStateException.class, () -> request.toModel(manager));

  }

  @Test
  @DisplayName("Cria um livro com sucesso a partir do livro request")
  public void estouraExceptionParaCategoriaNulo() {

    EntityManager manager = Mockito.mock(EntityManager.class);

    Mockito.when(manager.find(Autor.class, 2L)).thenReturn(new Autor("", "", ""));
    Mockito.when(manager.find(Category.class, 1L)).thenReturn(null);

    Assertions.assertThrows(IllegalStateException.class, () -> request.toModel(manager));

  }

}