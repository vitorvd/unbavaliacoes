package br.com.unbhelp.mapper;

import br.com.unbhelp.models.AvaliacaoTurma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliacaoTurmaRowMapper implements RowMapper<AvaliacaoTurma> {

    @Override
    public AvaliacaoTurma mapRow(ResultSet rs, int rowNum) throws SQLException {
        AvaliacaoTurma avaliacaoTurma = new AvaliacaoTurma();
        avaliacaoTurma.setId(rs.getInt("id"));
        avaliacaoTurma.setAvaliacao(rs.getString("avaliacao"));
        avaliacaoTurma.setNota(rs.getInt("nota"));
        avaliacaoTurma.setMatriculaEstudante(rs.getString("matricula_estudante"));
        avaliacaoTurma.setIdTurma(rs.getInt("id_turma"));
        return avaliacaoTurma;
    }

}