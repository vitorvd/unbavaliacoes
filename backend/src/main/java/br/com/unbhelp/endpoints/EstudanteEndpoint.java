package br.com.unbhelp.endpoints;

import br.com.unbhelp.models.Estudante;
import br.com.unbhelp.servicos.EstudanteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estudante")
public class EstudanteEndpoint {

    @Autowired
    private EstudanteServico servico;

    @PostMapping("/auth")
    public ResponseEntity autenticar(@RequestBody Estudante estudante) {
        try {
            Estudante estudanteRetornado = servico.autenticar(estudante);
            return ResponseEntity.ok(estudanteRetornado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> criarEstudante(@RequestBody Estudante estudante) {
        try {
            servico.criarEstudante(estudante);
            return ResponseEntity.ok("Estudante criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Estudante> buscarEstudante(@PathVariable String matricula) {
        Estudante estudante = servico.buscarEstudante(matricula);
        if (estudante != null) {
            return ResponseEntity.ok(estudante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> atualizarEstudante(@RequestBody Estudante estudante) {
        try {
            servico.atualizarEstudante(estudante);
            return ResponseEntity.ok("Estudante atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> excluirEstudante(@PathVariable String matricula) {
        try {
            servico.excluirEstudante(matricula);
            return ResponseEntity.ok("Estudante excluído com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
