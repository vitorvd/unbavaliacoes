package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.ProfessorRowMapper;
import br.com.unbhelp.mapper.TurmaRowMapper;
import br.com.unbhelp.models.Professor;
import br.com.unbhelp.models.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessorDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void criarProfessor(Professor professor) {
        String sql = "INSERT INTO professores (nome, departamento_id) VALUES (?, ?)";
        jdbc.update(sql, professor.getNome(), professor.getDepartamentoId());
    }

    public Professor buscarProfessor(Integer id) {
        String sql = "SELECT professores.id, professores.nome, professores.departamento_id, departamentos.nome as departamento FROM professores " +
                "JOIN departamentos ON professores.departamento_id = departamentos.id WHERE professores.id = ?;";

        Professor professor;
        try{
            professor = jdbc.queryForObject(sql, new ProfessorRowMapper(), new Object[]{id});
        }catch (EmptyResultDataAccessException ex) {
            professor = null;
        }

        return professor;
    }

    public Professor buscarProfessorPorNome(String nome) {
        String sql = "SELECT * FROM professores WHERE nome = ?;";

        Professor professor;
        try{
            professor = jdbc.queryForObject(sql, new ProfessorRowMapper(), new Object[]{nome});
        }catch (EmptyResultDataAccessException ex) {
            professor = null;
        }

        return professor;
    }

    public List<Professor> listarProfessores() {
        String sql = "SELECT * FROM professores;";
        return jdbc.query(sql, new ProfessorRowMapper());
    }

    public void atualizarProfessor(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, departamento_id = ? WHERE id = ?";
        jdbc.update(sql, professor.getNome(), professor.getDepartamentoId(), professor.getId());
    }

    public void excluirProfessor(Integer id) {
        String sql = "DELETE FROM professores WHERE id = ?";
        jdbc.update(sql, id);
    }
    
}
