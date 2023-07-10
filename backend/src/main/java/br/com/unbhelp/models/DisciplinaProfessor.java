package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaProfessor {

    private Integer id;
    private Disciplina disciplina;
    private Professor professor;

}
