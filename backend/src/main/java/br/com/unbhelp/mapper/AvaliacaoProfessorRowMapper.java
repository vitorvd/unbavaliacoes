package br.com.unbhelp.mapper;

import br.com.unbhelp.models.AvaliacaoProfessor;
import br.com.unbhelp.models.AvaliacaoTurma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliacaoProfessorRowMapper implements RowMapper<AvaliacaoProfessor> {

    @Override
    public AvaliacaoProfessor mapRow(ResultSet rs, int rowNum) throws SQLException {
        AvaliacaoProfessor avaliacaoProfessor = new AvaliacaoProfessor();
        avaliacaoProfessor.setId(rs.getInt("id"));
        avaliacaoProfessor.setAvaliacao(rs.getString("avaliacao"));
        avaliacaoProfessor.setNota(rs.getInt("nota"));
        avaliacaoProfessor.setMatriculaEstudante(rs.getString("matricula_estudante"));
        avaliacaoProfessor.setIdProfessor(rs.getInt("id_professor"));
        return avaliacaoProfessor;
    }

}