package br.com.unbhelp.mapper;

import br.com.unbhelp.models.ProfessorRanking;
import br.com.unbhelp.models.TurmaRanking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorRankingRowMapper implements RowMapper<ProfessorRanking> {

    @Override
    public ProfessorRanking mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfessorRanking professorRanking = new ProfessorRanking();
        professorRanking.setId(rs.getInt("id"));
        professorRanking.setProfessor(rs.getString("nome"));
        professorRanking.setMedia(rs.getDouble("media_avaliacoes"));
        return professorRanking;
    }
}
