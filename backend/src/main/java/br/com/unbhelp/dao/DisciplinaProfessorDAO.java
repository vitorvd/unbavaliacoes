package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.DepartamentoRowMapper;
import br.com.unbhelp.mapper.DisciplinaProfessorRowMapper;
import br.com.unbhelp.mapper.DisciplinaRowMapper;
import br.com.unbhelp.models.Departamento;
import br.com.unbhelp.models.Disciplina;
import br.com.unbhelp.models.DisciplinaProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DisciplinaProfessorDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void criarDisciplinaProfessor(Integer disciplinaId, Integer professorId){
        String sql = "INSERT INTO disciplinas_professores (disciplina_id, professor_id) VALUES (?, ?);";
        jdbc.update(sql, disciplinaId, professorId);
    }

    public List<DisciplinaProfessor> listarDisciplinasProfessores() {
        String sql = "SELECT dp.id, dp.disciplina_id, dp.professor_id, disciplinas.nome as disciplina, professores.nome as professor FROM disciplinas_professores AS dp " +
                "JOIN disciplinas ON dp.disciplina_id = disciplinas.id " +
                "JOIN professores ON dp.professor_id = professores.id;";
        return jdbc.query(sql, new DisciplinaProfessorRowMapper());
    }

    public DisciplinaProfessor getDisciplinaProfessorByProfessorId(Integer professorId) {
        String sql = "SELECT dp.id, dp.disciplina_id, dp.professor_id, disciplinas.nome as disciplina, professores.nome as professor FROM disciplinas_professores AS dp " +
                "JOIN disciplinas ON dp.disciplina_id = disciplinas.id " +
                "JOIN professores ON dp.professor_id = professores.id WHERE professor_id = ?;";
        return jdbc.queryForObject(sql, new DisciplinaProfessorRowMapper(), new Object[]{professorId});
    }

    public void atualizarDisciplinaDoProfessor(Integer disciplinaId, Integer professorId) {
        String sql = "UPDATE disciplinas_professores SET disciplina_id = ? WHERE professor_id = ?";
        jdbc.update(sql, disciplinaId, professorId);
    }

    public List<Disciplina> listarDisciplinas() {
        String sql = "SELECT * FROM disciplinas;";
        return jdbc.query(sql, new DisciplinaRowMapper());
    }

    public List<Departamento> listarDepartamentos() {
        String sql = "SELECT * FROM departamentos;";
        return jdbc.query(sql, new DepartamentoRowMapper());
    }

}
