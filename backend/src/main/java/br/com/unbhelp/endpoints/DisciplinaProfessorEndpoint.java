package br.com.unbhelp.endpoints;

import br.com.unbhelp.dao.DisciplinaProfessorDAO;
import br.com.unbhelp.models.Departamento;
import br.com.unbhelp.models.Disciplina;
import br.com.unbhelp.models.DisciplinaProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/disciplina-professor")
public class DisciplinaProfessorEndpoint {

    @Autowired
    private DisciplinaProfessorDAO dao;

    @GetMapping
    public ResponseEntity buscarDisciplinasProfessores() {
        List<DisciplinaProfessor> dpList = dao.listarDisciplinasProfessores();
        if (dpList.size() > 0)
            return ResponseEntity.ok(dpList);

        return ResponseEntity.badRequest().body("Não há nenhuma relação vinculada de Professor e Disciplina.");
    }

    @GetMapping("/disciplinas")
    public ResponseEntity buscarDisciplinas() {
        List<Disciplina> disciplinas = dao.listarDisciplinas();
        if (disciplinas.size() > 0)
            return ResponseEntity.ok(disciplinas);

        return ResponseEntity.badRequest().body("Não há nenhuma relação vinculada de Disciplina.");
    }

    @GetMapping("/departamentos")
    public ResponseEntity buscarDepartamentos() {
        List<Departamento> departamentos = dao.listarDepartamentos();
        if (departamentos.size() > 0)
            return ResponseEntity.ok(departamentos);

        return ResponseEntity.badRequest().body("Não há nenhuma relação vinculada Departamento.");
    }

}
