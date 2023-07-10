package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Estudante;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudanteRowMapper implements RowMapper<Estudante> {

    @Override
    public Estudante mapRow(ResultSet rs, int rowNum) throws SQLException {
        Estudante estudante = Estudante.builder()
                .matricula(rs.getString("matricula"))
                .email(rs.getString("email"))
                .curso(rs.getString("curso"))
                .senha(rs.getString("senha"))
                .administrador(rs.getBoolean("administrador")).build();

        return estudante;
    }
}
