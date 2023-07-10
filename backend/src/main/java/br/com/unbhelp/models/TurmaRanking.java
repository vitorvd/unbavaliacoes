package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaRanking {

    private Integer id;

    private String periodo;

    private String professor;

    private String disciplina;

    private Double media;

}
