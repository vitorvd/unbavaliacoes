-- Tabela: Estudantes
INSERT INTO estudantes(matricula, email, senha, curso, administrador) VALUES ('admin', 'admin@aluno.unb.br', 123, 'Computação - Licenciatura', true);
INSERT INTO estudantes(matricula, email, senha, curso, administrador) VALUES ('211060737', '211060737@aluno.unb.br', 123, 'Ciências da Computação', false);
INSERT INTO estudantes(matricula, email, senha, curso, administrador) VALUES ('150520020', '150520020@aluno.unb.br', 123, 'Matemática', false);

-- Tabela: Departamentos
INSERT INTO departamentos(nome) VALUES ('Ciências da Computação');
INSERT INTO departamentos(nome) VALUES ('Matemática');
INSERT INTO departamentos(nome) VALUES ('Ciências Biológicas');

-- Tabela: Disciplinas
INSERT INTO disciplinas(nome, departamento_id) VALUES ('Técnicas de Programação 1', 1);
INSERT INTO disciplinas(nome, departamento_id) VALUES ('Cálculo 1', 2);
INSERT INTO disciplinas(nome, departamento_id) VALUES ('Sistema Nervoso', 3);

-- Tabela: Professores
INSERT INTO professores(nome, departamento_id) VALUES ('Fernando Chacon', 1);
INSERT INTO professores(nome, departamento_id) VALUES ('Fernanda TP1', 1);
INSERT INTO professores(nome, departamento_id) VALUES ('João de Matemática', 2);
INSERT INTO professores(nome, departamento_id) VALUES ('Pedro de Matemática', 2);
INSERT INTO professores(nome, departamento_id) VALUES ('Letícia de Biologia', 3);
INSERT INTO professores(nome, departamento_id) VALUES ('Vanessa de Biologia', 3);

-- Tabela: DisciplinasProfessores
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (1, 1);
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (1, 2);
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (2, 3);
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (2, 4);
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (3, 5);
INSERT INTO disciplinas_professores(disciplina_id, professor_id) VALUES (3, 6);

-- Tabela: Turmas
INSERT INTO turmas(periodo, disciplina_id, professor_id) VALUES ('2023.1', 1, 1);
INSERT INTO turmas(periodo, disciplina_id, professor_id) VALUES ('2022.1', 2, 3);
INSERT INTO turmas(periodo, disciplina_id, professor_id) VALUES ('2022.2', 3, 5);

-- Tabela: AvaliacaoTurma
INSERT INTO avaliacoes_turmas(avaliacao, nota, matricula_estudante, id_turma) VALUES ('TP1 com o Chacon é bem didático!', 5, '211060737', 2);
INSERT INTO avaliacoes_turmas(avaliacao, nota, matricula_estudante, id_turma) VALUES ('C1 com João é difícil pra caramba', 2, 'admin', 3);
INSERT INTO avaliacoes_turmas(avaliacao, nota, matricula_estudante, id_turma) VALUES ('SN fica fácil com a Letícia!', 5, 'admin', 4);

-- Tabela: AvaliacaoProfessor
INSERT INTO avaliacoes_professores(avaliacao, nota, matricula_estudante, id_professor) VALUES ('Chacon é o mestre de SOLID!', 5, '211060737', 1);
INSERT INTO avaliacoes_professores(avaliacao, nota, matricula_estudante, id_professor) VALUES ('Professora incrível de Java', 2, 'admin', 2);
INSERT INTO avaliacoes_professores(avaliacao, nota, matricula_estudante, id_professor) VALUES ('João é um professor ok', 2, 'admin', 3);