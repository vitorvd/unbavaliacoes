package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Professor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorRowMapper implements RowMapper<Professor> {

    @Override
    public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Professor professor = new Professor();
        professor.setId(rs.getInt("id"));
        professor.setNome(rs.getString("nome"));
        professor.setDepartamentoId(rs.getInt("departamento_id"));

        return professor;
    }

}