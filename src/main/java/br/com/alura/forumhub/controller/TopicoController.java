package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.TopicoDTO;
import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<TopicoDTO> listar() {
        return topicoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> detalhar(@PathVariable Long id) {
        return topicoService.detalhar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> criar(@Valid @RequestBody Topico topico) {
        TopicoDTO criado = topicoService.criar(topico);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Topico topico) {
        TopicoDTO atualizado = topicoService.atualizar(id, topico);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
