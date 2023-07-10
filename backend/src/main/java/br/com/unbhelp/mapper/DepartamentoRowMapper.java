package br.com.unbhelp.mapper;

import br.com.unbhelp.models.Departamento;
import br.com.unbhelp.models.Disciplina;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoRowMapper implements RowMapper<Departamento> {

    @Override
    public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getInt("id"));
        departamento.setNome(rs.getString("nome"));

        return departamento;
    }
}
