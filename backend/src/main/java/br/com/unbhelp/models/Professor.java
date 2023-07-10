package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    private Integer id;

    private String nome;

    private Integer departamentoId;

    private Integer disciplinaId;

}
