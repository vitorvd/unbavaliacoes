package br.com.unbhelp.endpoints;

import br.com.unbhelp.models.AvaliacaoProfessor;
import br.com.unbhelp.servicos.AvaliacaoProfessorServico;
import br.com.unbhelp.servicos.AvaliacaoProfessorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/avaliacoes-professor")
public class AvaliacaoProfessorEndpoint {

    @Autowired
    private AvaliacaoProfessorServico servico;
    
    @PostMapping
    public ResponseEntity<String> criarAvaliacaoProfessor(@RequestBody AvaliacaoProfessor avaliacaoProfessor) {
        try {
            servico.criarAvaliacaoProfessor(avaliacaoProfessor);
            return ResponseEntity.ok("Avaliação de professor cadastrada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAvaliacaoProfessor(@PathVariable Integer id) {
        try {
            AvaliacaoProfessor avaliacaoProfessor = servico.buscarAvaliacaoProfessor(id);
            return ResponseEntity.ok(avaliacaoProfessor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AvaliacaoProfessor>> listarAvaliacoesProfessor(@PathVariable Integer professorId) {
        List<AvaliacaoProfessor> avaliacoesTurma = servico.listarAvaliacoesProfessor(professorId);
        return ResponseEntity.ok(avaliacoesTurma);
    }

    @PutMapping
    public ResponseEntity<String> atualizarAvaliacaoProfessor(@RequestBody AvaliacaoProfessor avaliacaoProfessor) {
        try {
            servico.atualizarAvaliacaoProfessor(avaliacaoProfessor);
            return ResponseEntity.ok("Avaliação da professor atualizada com sucesso.");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirAvaliacaoProfessor(@PathVariable Integer id) {
        try {
            servico.excluirAvaliacaoProfessor(id);
            return ResponseEntity.ok("Avaliação da professor excluída com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
