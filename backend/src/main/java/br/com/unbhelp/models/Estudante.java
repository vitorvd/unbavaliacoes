package br.com.unbhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estudante {

    private String matricula;

    private String email;

    private String senha;

    private String curso;

    private Boolean administrador;

}
