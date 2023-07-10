package br.com.unbhelp.servicos;

import br.com.unbhelp.dao.TurmaDAO;
import br.com.unbhelp.models.Turma;
import br.com.unbhelp.models.TurmaRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TurmaServico {
    
    @Autowired
    private TurmaDAO dao;
    
    public void criarTurma(Turma turma) {
        validarCamposObrigatorios(turma);
        dao.criarTurma(turma);
    }

    public Turma buscarTurma(Integer id) {
        return dao.buscarTurma(id);
    }

    public List<Turma> listarTurmas() {
        return dao.listarTurmas();
    }

    public void atualizarTurma(Turma turma) {
        validarCamposObrigatorios(turma);

        Turma turmaExistente = dao.buscarTurma(turma.getId());
        if(turmaExistente == null)
            throw new NoSuchElementException("Turma não encontrada.");

        dao.atualizarTurma(turma);
    }

    public void excluirTurma(Integer id) {
        Turma turma = dao.buscarTurma(id);

        if(turma == null)
            throw new NoSuchElementException("Turma não encontrada.");

        dao.excluirTurma(id);
    }

    public List<TurmaRanking> getRankingTurmas(){
        return dao.getRankingTurmas();
    }

    private void validarCamposObrigatorios(Turma turma) {
        if (turma.getPeriodo() == null || turma.getDisciplina() == null || turma.getDisciplina().getId() == null || turma.getProfessor() == null || turma.getProfessor().getId() == null)
            throw new IllegalArgumentException("Os campos: Periodo, Disciplina e Professor são obrigatórios.");
    }
    
}
