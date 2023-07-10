package br.com.unbhelp.endpoints;

import br.com.unbhelp.models.AvaliacaoTurma;
import br.com.unbhelp.servicos.AvaliacaoTurmaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/avaliacoes-turma")
public class AvaliacaoTurmaEndpoint {

    @Autowired
    private AvaliacaoTurmaServico servico;
    
    @PostMapping
    public ResponseEntity<String> criarAvaliacaoTurma(@RequestBody AvaliacaoTurma avaliacaoTurma) {
        try {
            servico.criarAvaliacaoTurma(avaliacaoTurma);
            return ResponseEntity.ok("Avaliação de turma cadastrada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAvaliacaoTurma(@PathVariable Integer id) {
        try {
            AvaliacaoTurma avaliacaoTurma = servico.buscarAvaliacaoTurma(id);
            return ResponseEntity.ok(avaliacaoTurma);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<AvaliacaoTurma>> listarAvaliacoesTurma(@PathVariable Integer turmaId) {
        List<AvaliacaoTurma> avaliacoesTurma = servico.listarAvaliacoesTurma(turmaId);
        return ResponseEntity.ok(avaliacoesTurma);
    }

    @PutMapping
    public ResponseEntity<String> atualizarAvaliacaoTurma(@RequestBody AvaliacaoTurma avaliacaoTurma) {
        try {
            servico.atualizarAvaliacaoTurma(avaliacaoTurma);
            return ResponseEntity.ok("Avaliação da turma atualizada com sucesso.");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirAvaliacaoTurma(@PathVariable Integer id) {
        try {
            servico.excluirAvaliacaoTurma(id);
            return ResponseEntity.ok("Avaliação da turma excluída com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
