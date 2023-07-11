# UnB Avaliacções (Backend)
Projeto final desenvolvido para a conclusão da disciplia da banco de dados

# Como rodar localmente?

- O que é necessário ter instalado?
  - Java 17 e JDK 17 (O projeto foi desenvolvido usando Java 8, mas não tem problema pois ele é retrocompatível);
  - Docker e Docker-compose;
  - Maven 3.8.8;

Passo a passo:
1. No mesmo diretório que estiver a pasta "src":
   1. Digite: `mvn clean package -DskipTests=true`. Isso vai gerar um build do projeto;
   2. No mesmo diretório, digite:
      1. `docker-compose build`
      2. `docker-compose up`
         1. Com isso, você terá dois containers rodando localmente: O backend do projeto e o banco de dados.
         2. O servidor ficará disponível em: `http://localhost:8080/`

Scripts SQL do Banco de dados:
- Não é necessário a execução de nenhum desses scripts para rodar o projeto localmente, com exceção do "Inserção dos dados".
- O sistema cria automaticamente o Schema, Procedures e Views.
1. (Schema)[https://github.com/vitorvd/unbavaliacoes/blob/master/backend/src/main/resources/tabelas.sql]
2. (Inserção dos dados)[https://github.com/vitorvd/unbavaliacoes/blob/master/backend/src/main/resources/data.sql]
3. (Procedures)[https://github.com/vitorvd/unbavaliacoes/blob/master/backend/src/main/resources/procedures.sql]
4. (Views)[https://github.com/vitorvd/unbavaliacoes/blob/master/backend/src/main/resources/views.sql]

