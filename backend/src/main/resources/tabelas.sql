CREATE TABLE IF NOT EXISTS estudantes(
    matricula VARCHAR(9) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    curso VARCHAR(50) NOT NULL,
    administrador BOOLEAN,
    PRIMARY KEY (matricula)
    );

CREATE TABLE IF NOT EXISTS departamentos(
    id SERIAL,
    nome VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS professores(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamentos (id)
    );

CREATE TABLE IF NOT EXISTS disciplinas(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamentos (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS turmas(
    id SERIAL PRIMARY KEY,
    periodo VARCHAR(6) NOT NULL,
    disciplina_id INT,
    professor_id INT,
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas (id) ON DELETE CASCADE,
    FOREIGN KEY (professor_id) REFERENCES professores (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS avaliacoes_turmas(
    id SERIAL PRIMARY KEY,
    avaliacao TEXT NOT NULL,
    nota INT NOT NULL,
    matricula_estudante VARCHAR(9) NOT NULL,
    id_turma INT NOT NULL,
    FOREIGN KEY (matricula_estudante) REFERENCES estudantes (matricula) ON DELETE CASCADE,
    FOREIGN KEY (id_turma) REFERENCES turmas (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS avaliacoes_professores(
    id SERIAL PRIMARY KEY,
    avaliacao TEXT NOT NULL,
    nota INT NOT NULL,
    matricula_estudante VARCHAR(9) NOT NULL,
    id_professor INT NOT NULL,
    FOREIGN KEY (matricula_estudante) REFERENCES estudantes (matricula) ON DELETE CASCADE,
    FOREIGN KEY (id_professor) REFERENCES professores (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS disciplinas_professores(
    id SERIAL PRIMARY KEY,
    disciplina_id INT,
    professor_id INT,
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas (id) ON DELETE CASCADE,
    FOREIGN KEY (professor_id) REFERENCES professores (id) ON DELETE CASCADE
    );