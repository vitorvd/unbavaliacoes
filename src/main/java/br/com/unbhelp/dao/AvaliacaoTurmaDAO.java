package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.AvaliacaoTurmaRowMapper;
import br.com.unbhelp.models.AvaliacaoTurma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoTurmaDAO {

    @Autowired
    private JdbcTemplate jdbc;
    
    public void criarAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        String sql = "INSERT INTO avaliacoes_turmas (avaliacao, nota, matricula_estudante, id_turma) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, avaliacaoTurma.getAvaliacao(), avaliacaoTurma.getNota(), avaliacaoTurma.getMatriculaEstudante(), avaliacaoTurma.getIdTurma());
    }

    public AvaliacaoTurma buscarAvaliacaoTurma(Integer id) {
        String sql = "SELECT * FROM avaliacoes_turmas WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, new AvaliacaoTurmaRowMapper());
    }

    public List<AvaliacaoTurma> listarAvaliacoesTurma(Integer turmaId) {
        String sql = "SELECT * FROM avaliacoes_turmas WHERE id_turma = ?";
        return jdbc.query(sql, new AvaliacaoTurmaRowMapper(), new Object[]{turmaId});
    }

    public void atualizarAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        String sql = "UPDATE avaliacoes_turmas SET avaliacao = ?, nota = ?, matricula_estudante = ?, id_turma = ? WHERE id = ?";
        jdbc.update(sql, avaliacaoTurma.getAvaliacao(), avaliacaoTurma.getNota(),
                avaliacaoTurma.getMatriculaEstudante(), avaliacaoTurma.getIdTurma(), avaliacaoTurma.getId());
    }

    public void excluirAvaliacaoTurma(Integer id) {
        String sql = "DELETE FROM avaliacoes_turmas WHERE id = ?";
        jdbc.update(sql, id);
    }
}