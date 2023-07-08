package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Turma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurmaRowMapper implements RowMapper<Turma> {

    @Override
    public Turma mapRow(ResultSet rs, int rowNum) throws SQLException {
        Turma turma = new Turma();
        turma.setId(rs.getInt("id"));
        turma.setPeriodo(rs.getString("periodo"));
        turma.setDisciplinaId(rs.getInt("disciplina_id"));
        turma.setProfessorId(rs.getInt("professor_id"));
        return turma;
    }

}