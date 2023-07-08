package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {

    private Integer Id;

    private String periodo;

    private Integer disciplinaId;

    private Integer professorId;

}
