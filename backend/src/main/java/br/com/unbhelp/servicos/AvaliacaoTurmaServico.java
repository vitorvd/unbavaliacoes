package br.com.unbhelp.servicos;

import br.com.unbhelp.dao.AvaliacaoTurmaDAO;
import br.com.unbhelp.dao.EstudanteDAO;
import br.com.unbhelp.models.AvaliacaoTurma;
import br.com.unbhelp.models.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoTurmaServico {

    @Autowired
    private AvaliacaoTurmaDAO dao;

    @Autowired
    private EstudanteDAO estudanteDAO;

    public void criarAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        validarCamposObrigatorios(avaliacaoTurma);

        dao.criarAvaliacaoTurma(avaliacaoTurma);
    }

    public AvaliacaoTurma buscarAvaliacaoTurma(Integer id) {
        return dao.buscarAvaliacaoTurma(id);
    }

    public List<AvaliacaoTurma> listarAvaliacoesTurma(Integer turmaId) {
        return dao.listarAvaliacoesTurma(turmaId);
    }

    public void atualizarAvaliacaoTurma(AvaliacaoTurma avaliacaoTurma) {
        validarCamposObrigatorios(avaliacaoTurma);

        dao.atualizarAvaliacaoTurma(avaliacaoTurma);
    }

    public void excluirAvaliacaoTurma(Integer id) {
        dao.excluirAvaliacaoTurma(id);
    }

    private void validarCamposObrigatorios(AvaliacaoTurma avaliacaoTurma) {
        if (avaliacaoTurma.getAvaliacao() == null || avaliacaoTurma.getMatriculaEstudante() == null || avaliacaoTurma.getIdTurma() == null)
            throw new IllegalArgumentException("Os campos: Avaliacação e Turma são obrigatórios.");
    }

}
