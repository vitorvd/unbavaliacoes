package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.TurmaRowMapper;
import br.com.unbhelp.models.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TurmaDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void criarTurma(Turma turma) {
        String sql = "INSERT INTO turmas (periodo, disciplina_id, professor_id) VALUES (?, ?, ?)";
        jdbc.update(sql, turma.getPeriodo(), turma.getDisciplinaId(), turma.getProfessorId());
    }

    public Turma buscarTurma(Integer id) {
        String sql = "SELECT * FROM turmas WHERE id = ?";

        Turma turma;
        try{
            turma = jdbc.queryForObject(sql, new TurmaRowMapper(), new Object[]{id});
        }catch (EmptyResultDataAccessException ex) {
            turma = null;
        }

        return turma;
    }

    public List<Turma> listarTurmas() {
        String sql = "SELECT * FROM turmas";
        return jdbc.query(sql, new TurmaRowMapper());
    }

    public void atualizarTurma(Turma turma) {
        String sql = "UPDATE turmas SET periodo = ?, disciplina_id = ?, professor_id = ? WHERE id = ?";
        jdbc.update(sql, turma.getPeriodo(), turma.getDisciplinaId(), turma.getProfessorId(), turma.getId());
    }

    public void excluirTurma(Integer id) {
        String sql = "DELETE FROM turmas WHERE id = ?";
        jdbc.update(sql, id);
    }
    
}
