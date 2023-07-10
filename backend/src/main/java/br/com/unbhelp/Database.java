package br.com.unbhelp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Database implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void run(String... args) throws Exception {
        log.info("Criando tabelas...");

        //Tabela Estudante
        jdbc.execute("CREATE TABLE IF NOT EXISTS estudantes(" +
                "matricula VARCHAR(9) NOT NULL, " +
                "email VARCHAR(50) NOT NULL, " +
                "senha VARCHAR(20) NOT NULL, " +
                "curso VARCHAR(50) NOT NULL, " +
                "administrador BOOLEAN," +
                "PRIMARY KEY (matricula)" +
                ")");

        //Tabela Departamento
        jdbc.execute("CREATE TABLE IF NOT EXISTS departamentos(" +
                "id SERIAL, " +
                "nome VARCHAR(30) NOT NULL, " +
                "PRIMARY KEY (id)" +
                ")");

        //Tabela Professor
        jdbc.execute("CREATE TABLE IF NOT EXISTS professores(" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(100) NOT NULL, " +
                "departamento_id INT," +
                "FOREIGN KEY (departamento_id) REFERENCES departamentos (id)" +
                ")");

        //Tabela Disciplinas
        jdbc.execute("CREATE TABLE IF NOT EXISTS disciplinas(" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(50) NOT NULL, " +
                "departamento_id INT," +
                "FOREIGN KEY (departamento_id) REFERENCES departamentos (id) ON DELETE CASCADE" +
                ")");

        //Tabela Turmas
        jdbc.execute("CREATE TABLE IF NOT EXISTS turmas(" +
                "id SERIAL PRIMARY KEY, " +
                "periodo VARCHAR(6) NOT NULL, " +
                "disciplina_id INT, " +
                "professor_id INT, " +
                "FOREIGN KEY (disciplina_id) REFERENCES disciplinas (id) ON DELETE CASCADE, " +
                "FOREIGN KEY (professor_id) REFERENCES professores (id) ON DELETE CASCADE" +
                ")");

        //Tabela AvaliacoesTurmas
        jdbc.execute("CREATE TABLE IF NOT EXISTS avaliacoes_turmas(" +
                "id SERIAL PRIMARY KEY, " +
                "avaliacao TEXT NOT NULL," +
                "nota INT NOT NULL, " +
                "matricula_estudante VARCHAR(9) NOT NULL," +
                "id_turma INT NOT NULL, " +
                "FOREIGN KEY (matricula_estudante) REFERENCES estudantes (matricula) ON DELETE CASCADE, " +
                "FOREIGN KEY (id_turma) REFERENCES turmas (id) ON DELETE CASCADE" +
                ")");

        //Tabela AvaliacoesProfessor
        jdbc.execute("CREATE TABLE IF NOT EXISTS avaliacoes_professores(" +
                "id SERIAL PRIMARY KEY, " +
                "avaliacao TEXT NOT NULL," +
                "nota INT NOT NULL, " +
                "matricula_estudante VARCHAR(9) NOT NULL," +
                "id_professor INT NOT NULL, " +
                "FOREIGN KEY (matricula_estudante) REFERENCES estudantes (matricula) ON DELETE CASCADE, " +
                "FOREIGN KEY (id_professor) REFERENCES professores (id) ON DELETE CASCADE" +
                ")");

        //Tabela DisciplinaProfessor
        jdbc.execute("CREATE TABLE IF NOT EXISTS disciplinas_professores(" +
                "id SERIAL PRIMARY KEY, " +
                "disciplina_id INT, " +
                "professor_id INT, " +
                "FOREIGN KEY (disciplina_id) REFERENCES disciplinas (id) ON DELETE CASCADE, " +
                "FOREIGN KEY (professor_id) REFERENCES professores (id) ON DELETE CASCADE" +
                ")");

    }

}
