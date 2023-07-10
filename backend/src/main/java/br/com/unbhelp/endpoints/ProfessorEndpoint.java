package br.com.unbhelp.endpoints;

import br.com.unbhelp.models.Professor;
import br.com.unbhelp.models.ProfessorRanking;
import br.com.unbhelp.models.TurmaRanking;
import br.com.unbhelp.servicos.ProfessorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/professor")
public class ProfessorEndpoint {

    @Autowired
    private ProfessorServico servico;

    @PostMapping
    public ResponseEntity<String> criarProfessor(@RequestBody Professor professor) {
        try {
            servico.criarProfessor(professor);
            return ResponseEntity.ok("Professor criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable Integer id) {
        try {
            Professor professor = servico.buscarProfessor(id);
            return ResponseEntity.ok(professor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessors() {
        List<Professor> professors = servico.listarProfessors();
        return ResponseEntity.ok(professors);
    }

    @PutMapping
    public ResponseEntity<String> atualizarProfessor(@RequestBody Professor professor) {
        try {
            servico.atualizarProfessor(professor);
            return ResponseEntity.ok("Professor atualizado com sucesso.");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProfessor(@PathVariable Integer id) {
        try {
            servico.excluirProfessor(id);
            return ResponseEntity.ok("Professor excluido com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ProfessorRanking>> getRanking() {
        List<ProfessorRanking> professores = servico.getRankingProfessores();
        return ResponseEntity.ok(professores);
    }

}
