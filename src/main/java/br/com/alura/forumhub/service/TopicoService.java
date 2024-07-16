package br.com.alura.forumhub.service;

import br.com.alura.forumhub.dto.TopicoDTO;
import br.com.alura.forumhub.exception.ResourceNotFoundException;
import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public List<TopicoDTO> listar() {
        return topicoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<TopicoDTO> detalhar(Long id) {
        return topicoRepository.findById(id).map(this::toDTO);
    }

    @Transactional
    public TopicoDTO criar(Topico topico) {
        if (topicoRepository.existsByTituloAndMensagem(topico.getTitulo(), topico.getMensagem())) {
            throw new IllegalArgumentException("Tópico já existente.");
        }
        return toDTO(topicoRepository.save(topico));
    }

    @Transactional
    public TopicoDTO atualizar(Long id, Topico topicoAtualizado) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));
        topico.setTitulo(topicoAtualizado.getTitulo());
        topico.setMensagem(topicoAtualizado.getMensagem());
        topico.setStatus(topicoAtualizado.getStatus());
        return toDTO(topicoRepository.save(topico));
    }

    @Transactional
    public void deletar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tópico não encontrado.");
        }
        topicoRepository.deleteById(id);
    }

    private TopicoDTO toDTO(Topico topico) {
        TopicoDTO dto = new TopicoDTO();
        dto.setId(topico.getId());
        dto.setTitulo(topico.getTitulo());
        dto.setMensagem(topico.getMensagem());
        dto.setDataCriacao(topico.getDataCriacao());
        dto.setStatus(topico.getStatus());
        dto.setNomeAutor(topico.getAutor().getNome());
        dto.setNomeCurso(topico.getCurso().getNome());
        return dto;
    }
}
