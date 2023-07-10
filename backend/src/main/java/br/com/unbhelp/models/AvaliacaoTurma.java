package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacaoTurma {

    private Integer id;
    private String avaliacao;
    private int nota;
    private String matriculaEstudante;
    private Integer idTurma;

}
