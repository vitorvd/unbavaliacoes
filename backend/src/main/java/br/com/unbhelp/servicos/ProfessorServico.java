package br.com.unbhelp.servicos;


import br.com.unbhelp.dao.DisciplinaProfessorDAO;
import br.com.unbhelp.dao.ProfessorDAO;
import br.com.unbhelp.models.DisciplinaProfessor;
import br.com.unbhelp.models.Professor;
import br.com.unbhelp.models.ProfessorRanking;
import br.com.unbhelp.models.TurmaRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessorServico {

    @Autowired
    private ProfessorDAO dao;

    @Autowired
    private DisciplinaProfessorDAO disciplinaProfessorDAO;

    public void criarProfessor(Professor professor) {
        validarCamposObrigatorios(professor);
        dao.criarProfessor(professor);

        Professor professorCriado = dao.buscarProfessorPorNome(professor.getNome());
        disciplinaProfessorDAO.criarDisciplinaProfessor(professor.getDisciplinaId(), professorCriado.getId());
    }

    public Professor buscarProfessor(Integer id) {
        return dao.buscarProfessor(id);
    }

    public List<Professor> listarProfessors() {
        List<Professor> professores = dao.listarProfessores();
        professores.forEach(prof -> {
            DisciplinaProfessor dp = disciplinaProfessorDAO.getDisciplinaProfessorByProfessorId(prof.getId());
            prof.setDisciplinaId(dp.getDisciplina().getId());
        });
        return professores;
    }

    public void atualizarProfessor(Professor professor) {
        validarCamposObrigatorios(professor);

        Professor professorExistente = dao.buscarProfessor(professor.getId());
        if(professorExistente == null)
            throw new NoSuchElementException("Professor não encontrada.");

        disciplinaProfessorDAO.atualizarDisciplinaDoProfessor(professor.getDisciplinaId(), professor.getId());

        dao.atualizarProfessor(professor);
    }

    public void excluirProfessor(Integer id) {
        Professor professor = dao.buscarProfessor(id);

        if(professor == null)
            throw new NoSuchElementException("Professor não encontrada.");

        dao.excluirProfessor(id);
    }

    public List<ProfessorRanking> getRankingProfessores(){
        return dao.getRankingProfessores();
    }

    private void validarCamposObrigatorios(Professor professor) {
        if (professor.getNome() == null)
            throw new IllegalArgumentException("Os campos: Nome do professor são obrigatórios.");
    }

}

