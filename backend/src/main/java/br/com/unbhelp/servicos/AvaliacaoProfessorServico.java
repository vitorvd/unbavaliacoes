package br.com.unbhelp.servicos;

import br.com.unbhelp.dao.AvaliacaoProfessorDAO;
import br.com.unbhelp.dao.AvaliacaoProfessorDAO;
import br.com.unbhelp.dao.EstudanteDAO;
import br.com.unbhelp.models.AvaliacaoProfessor;
import br.com.unbhelp.models.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoProfessorServico {

    @Autowired
    private AvaliacaoProfessorDAO dao;

    @Autowired
    private EstudanteDAO estudanteDAO;

    public void criarAvaliacaoProfessor(AvaliacaoProfessor avaliacaoTurma) {
        validarCamposObrigatorios(avaliacaoTurma);

        dao.criarAvaliacaoProfessor(avaliacaoTurma);
    }

    public AvaliacaoProfessor buscarAvaliacaoProfessor(Integer id) {
        return dao.buscarAvaliacaoProfessor(id);
    }

    public List<AvaliacaoProfessor> listarAvaliacoesProfessor(Integer professorId) {
        return dao.listarAvaliacoesProfessor(professorId);
    }

    public void atualizarAvaliacaoProfessor(AvaliacaoProfessor avaliacaoTurma) {
        validarCamposObrigatorios(avaliacaoTurma);

        dao.atualizarAvaliacaoProfessor(avaliacaoTurma);
    }

    public void excluirAvaliacaoProfessor(Integer id) {
        dao.excluirAvaliacaoProfessor(id);
    }

    private void validarCamposObrigatorios(AvaliacaoProfessor avaliacaoTurma) {
        if (avaliacaoTurma.getAvaliacao() == null || avaliacaoTurma.getMatriculaEstudante() == null || avaliacaoTurma.getIdProfessor() == null)
            throw new IllegalArgumentException("Os campos: Avaliacação e Professor são obrigatórios.");
    }

}
