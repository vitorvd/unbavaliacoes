package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.AvaliacaoProfessorRowMapper;
import br.com.unbhelp.models.AvaliacaoProfessor;
import br.com.unbhelp.models.AvaliacaoProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoProfessorDAO {

    @Autowired
    private JdbcTemplate jdbc;
    
    public void criarAvaliacaoProfessor(AvaliacaoProfessor avaliacaoProfessor) {
        String sql = "INSERT INTO avaliacoes_professores (avaliacao, nota, matricula_estudante, id_professor) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, avaliacaoProfessor.getAvaliacao(), avaliacaoProfessor.getNota(), avaliacaoProfessor.getMatriculaEstudante(), avaliacaoProfessor.getIdProfessor());
    }

    public AvaliacaoProfessor buscarAvaliacaoProfessor(Integer id) {
        String sql = "SELECT * FROM avaliacoes_professores WHERE id = ?";
        return jdbc.queryForObject(sql, new AvaliacaoProfessorRowMapper(), new Object[]{id});
    }

    public List<AvaliacaoProfessor> listarAvaliacoesProfessor(Integer professorId) {
        String sql = "SELECT * FROM avaliacoes_professores WHERE id_professor = ?";
        return jdbc.query(sql, new AvaliacaoProfessorRowMapper(), new Object[]{professorId});
    }

    public void atualizarAvaliacaoProfessor(AvaliacaoProfessor avaliacaoProfessor) {
        String sql = "UPDATE avaliacoes_professores SET avaliacao = ?, nota = ?, matricula_estudante = ?, id_professor = ? WHERE id = ?";
        jdbc.update(sql, avaliacaoProfessor.getAvaliacao(), avaliacaoProfessor.getNota(),
                avaliacaoProfessor.getMatriculaEstudante(), avaliacaoProfessor.getIdProfessor(), avaliacaoProfessor.getId());
    }

    public void excluirAvaliacaoProfessor(Integer id) {
        String sql = "DELETE FROM avaliacoes_professores WHERE id = ?";
        jdbc.update(sql, id);
    }
}