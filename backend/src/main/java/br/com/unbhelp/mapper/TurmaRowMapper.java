package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Disciplina;
import br.com.unbhelp.models.Professor;
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

        Disciplina disciplina = Disciplina.builder().id(rs.getInt("disciplina_id")).nome(rs.getString("disciplina")).build();
        turma.setDisciplina(disciplina);

        Professor professor = Professor.builder().id(rs.getInt("professor_id")).nome(rs.getString("professor")).build();
        turma.setProfessor(professor);
        return turma;
    }

}