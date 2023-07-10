package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Disciplina;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisciplinaRowMapper implements RowMapper<Disciplina> {

    @Override
    public Disciplina mapRow(ResultSet rs, int rowNum) throws SQLException {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(rs.getInt("id"));
        disciplina.setNome(rs.getString("nome"));

        return disciplina;
    }
}
