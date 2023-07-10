package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Disciplina;
import br.com.unbhelp.models.DisciplinaProfessor;
import br.com.unbhelp.models.Professor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisciplinaProfessorRowMapper implements RowMapper<DisciplinaProfessor> {

    @Override
    public DisciplinaProfessor mapRow(ResultSet rs, int rowNum) throws SQLException {
        DisciplinaProfessor disciplinaProfessor = new DisciplinaProfessor();
        disciplinaProfessor.setId(rs.getInt("id"));

        Disciplina disciplina = Disciplina.builder().id(rs.getInt("disciplina_id")).nome(rs.getString("disciplina")).build();
        disciplinaProfessor.setDisciplina(disciplina);

        Professor professor = Professor.builder().id(rs.getInt("professor_id")).nome(rs.getString("professor")).build();
        disciplinaProfessor.setProfessor(professor);
        return disciplinaProfessor;
    }

}
