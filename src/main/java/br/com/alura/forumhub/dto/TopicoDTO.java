package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.model.StatusTopico;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String nomeAutor;
    private String nomeCurso;
}
