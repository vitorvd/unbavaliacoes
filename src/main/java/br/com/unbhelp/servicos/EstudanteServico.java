package br.com.unbhelp.servicos;

import br.com.unbhelp.dao.EstudanteDAO;
import br.com.unbhelp.models.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstudanteServico {

    @Autowired
    private EstudanteDAO dao;

    public void criarEstudante(Estudante estudante) {
        validarCamposNulos(estudante);

        Estudante estudanteExistente = dao.buscarEstudante(estudante.getMatricula());
        if (estudanteExistente != null)
            throw new IllegalStateException("Estudante já existe.");

        dao.criarEstudante(estudante);
    }

    public Estudante buscarEstudante(String matricula) {
        return dao.buscarEstudante(matricula);
    }

    public void atualizarEstudante(Estudante estudante) {
        validarCamposNulos(estudante);

        Estudante estudanteExistente = dao.buscarEstudante(estudante.getMatricula());
        if (estudanteExistente == null)
            throw new IllegalStateException("Estudante não existe.");

        dao.atualizarEstudante(estudante);
    }

    public void excluirEstudante(String matricula) {
        Estudante estudanteExistente = dao.buscarEstudante(matricula);

        if (estudanteExistente == null)
            throw new IllegalStateException("Estudante não existe.");

        dao.excluirEstudante(matricula);
    }

    private void validarCamposNulos(Estudante estudante){
        if (estudante.getMatricula() == null || estudante.getEmail() == null || estudante.getSenha() == null || estudante.getCurso() == null)
            throw new IllegalArgumentException("Os campos: Matricula, e-mail, curso e senha são obrigatórios.");
    }

}
