package br.com.alura.forumhub.repository;

import br.com.alura.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Topico t WHERE t.titulo = :titulo AND t.mensagem = :mensagem")
    boolean existsByTituloAndMensagem(@Param("titulo") String titulo, @Param("mensagem") String mensagem);
}
