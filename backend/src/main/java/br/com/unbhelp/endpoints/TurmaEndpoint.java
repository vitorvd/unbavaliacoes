package br.com.unbhelp.endpoints;

import br.com.unbhelp.models.Turma;
import br.com.unbhelp.models.TurmaRanking;
import br.com.unbhelp.servicos.TurmaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/turma")
public class TurmaEndpoint {

    @Autowired
    private TurmaServico servico;

    @PostMapping
    public ResponseEntity<String> criarTurma(@RequestBody Turma turma) {
        try {
            servico.criarTurma(turma);
            return ResponseEntity.ok("Turma criada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurma(@PathVariable Integer id) {
        try {
            Turma turma = servico.buscarTurma(id);
            return ResponseEntity.ok(turma);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = servico.listarTurmas();
        return ResponseEntity.ok(turmas);
    }

    @PutMapping
    public ResponseEntity<String> atualizarTurma(@RequestBody Turma turma) {
        try {
            servico.atualizarTurma(turma);
            return ResponseEntity.ok("Turma atualizada com sucesso.");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTurma(@PathVariable Integer id) {
        try {
            servico.excluirTurma(id);
            return ResponseEntity.ok("Turma excluída com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<TurmaRanking>> getRanking() {
        List<TurmaRanking> turmas = servico.getRankingTurmas();
        return ResponseEntity.ok(turmas);
    }

}
