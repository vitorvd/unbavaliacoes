package br.com.unbhelp.dao;

import br.com.unbhelp.mapper.EstudanteRowMapper;
import br.com.unbhelp.models.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EstudanteDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void criarEstudante(Estudante estudante) {
        String sql = "INSERT INTO estudantes (matricula, email, senha, curso, administrador) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql, estudante.getMatricula(), estudante.getEmail(), estudante.getSenha(),
                estudante.getCurso(), estudante.getAdministrador());
    }

    public Estudante buscarEstudante(String matricula) {
        String sql = "SELECT * FROM estudantes WHERE matricula = ?";

        Estudante estudante;
        try {
            estudante = jdbc.queryForObject(sql, new EstudanteRowMapper(), new Object[]{matricula});
        }catch (EmptyResultDataAccessException ex) {
            estudante = null;
        }

        return estudante;
    }

    public void atualizarEstudante(Estudante estudante) {
        String sql = "UPDATE estudantes SET email = ?, senha = ?, curso = ?, administrador = ? WHERE matricula = ?";
        jdbc.update(sql, estudante.getEmail(), estudante.getSenha(), estudante.getCurso(),
                estudante.getAdministrador(), estudante.getMatricula());
    }

    public void excluirEstudante(String matricula) {
        String sql = "DELETE FROM estudantes WHERE matricula = ?";
        jdbc.update(sql, matricula);
    }

}
