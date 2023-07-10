package br.com.unbhelp.mapper;

import br.com.unbhelp.models.TurmaRanking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurmaRankingRowMapper implements RowMapper<TurmaRanking> {

    @Override
    public TurmaRanking mapRow(ResultSet rs, int rowNum) throws SQLException {
        TurmaRanking turma = new TurmaRanking();
        turma.setId(rs.getInt("id"));
        turma.setPeriodo(rs.getString("periodo"));
        turma.setDisciplina(rs.getString("nome_disciplina"));
        turma.setProfessor(rs.getString("nome_professor"));
        turma.setMedia(rs.getDouble("media_avaliacoes"));
        return turma;
    }
}
