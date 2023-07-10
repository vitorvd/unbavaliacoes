-- Procedure: Inserção do Professor
CREATE or replace PROCEDURE inserir_professor(nome VARCHAR, departamento_id integer) LANGUAGE SQL
AS $$
    INSERT INTO professores (nome, departamento_id) VALUES (nome, departamento_id);
$$;