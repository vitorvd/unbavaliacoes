package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.TurmaRankingRowMapper;
import br.com.unbhelp.mapper.TurmaRowMapper;
import br.com.unbhelp.models.Turma;
import br.com.unbhelp.models.TurmaRanking;
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
        jdbc.update(sql, turma.getPeriodo(), turma.getDisciplina().getId(), turma.getProfessor().getId());
    }

    public Turma buscarTurma(Integer id) {
        String sql = "SELECT turmas.id, turmas.periodo, turmas.disciplina_id, turmas.professor_id, disciplinas.nome as disciplina, professores.nome as professor FROM turmas " +
                "JOIN disciplinas ON turmas.disciplina_id = disciplinas.id " +
                "JOIN professores ON turmas.professor_id = professores.id WHERE turmas.id = ?;";

        Turma turma;
        try{
            turma = jdbc.queryForObject(sql, new TurmaRowMapper(), new Object[]{id});
        }catch (EmptyResultDataAccessException ex) {
            turma = null;
        }

        return turma;
    }

    public List<Turma> listarTurmas() {
        String sql = "SELECT turmas.id, turmas.periodo, turmas.disciplina_id, turmas.professor_id, disciplinas.nome as disciplina, professores.nome as professor FROM turmas " +
                "JOIN disciplinas ON turmas.disciplina_id = disciplinas.id " +
                "JOIN professores ON turmas.professor_id = professores.id;";
        return jdbc.query(sql, new TurmaRowMapper());
    }

    public void atualizarTurma(Turma turma) {
        String sql = "UPDATE turmas SET periodo = ?, disciplina_id = ?, professor_id = ? WHERE id = ?";
        jdbc.update(sql, turma.getPeriodo(), turma.getDisciplina().getId(), turma.getProfessor().getId(), turma.getId());
    }

    public void excluirTurma(Integer id) {
        String sql = "DELETE FROM turmas WHERE id = ?";
        jdbc.update(sql, id);
    }

    public List<TurmaRanking> getRankingTurmas(){
        String sql = "SELECT * FROM turmas_melhores_avaliadas;";
        return jdbc.query(sql, new TurmaRankingRowMapper());
    }
    
}
