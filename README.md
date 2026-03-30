🗳️ Sistema de Votação V1 - API REST
Esta é uma solução profissional para o gerenciamento de sessões de votação em assembleias. A API permite a criação de pautas, abertura de sessões com tempo controlado e registro de votos, garantindo a integridade dos dados e regras de negócio robustas.

🚀 Tecnologias e Versões

Java 21

Spring Boot 3.3.0 (Framework para construção da API REST.)

Spring Data JPA (Persistência dos dados com Hibernate)

Spring Validation (Bean Validation)

PostgreSQL (Banco de dados de produção)

H2 Database (Banco em memória para testes)

JUnit 5 & Mockito (Cobertura de testes unitários)

Maven 3.8+

🏗️ Arquitetura e Padrões

O projeto foi desenvolvido seguindo as melhores práticas de Clean Code e SOLID:

Injeção de Dependência por Construtor: Maior testabilidade e imutabilidade.

Global Exception Handler: Padronização de erros em JSON para todos os endpoints.

DTOs com Records: Garantia de imutabilidade no tráfego de dados.

Soft Validation: Regras de negócio validadas antes da persistência.

🛠️ Como Executar

2. Clonar o Repositório
   Bash

git clone https://github.com/seu-usuario/votacaov1.git
cd votacaov1

3. Subir o Banco de Dados
   Bash

Com o Postgres instalado, criar um banco nomeado votacaov1
3. Compilar e Rodar a Aplicação
   Bash

mvn clean install
mvn spring-boot:run

📖 Endpoints da API

Pautas

POST /api/v1/pautas: Cria uma nova pauta para votação.

GET /api/v1/pautas/{id}/resultado: Exibe o placar final dos votos.

Sessões

POST /api/v1/sessoes/abrir: Abre uma pauta para votação.

Regra: Se não informar duracaoMinutos, o padrão será 1 minuto.

GET /api/v1/sessoes/{id}: Consulta o status de uma sessão específica.

Votos

POST /api/v1/votos: Registra o voto de um associado.

Regra 1: Um associado não pode votar duas vezes na mesma pauta.

Regra 2: Votos só são aceitos se a sessão ainda estiver aberta.

🧪 Executando Testes

Para garantir que todas as regras de negócio estão funcionando, execute:

Bash

mvn test

Nota sobre os Testes: Os testes utilizam o perfil test com banco H2 em memória, não afetando os dados do seu banco PostgreSQL local.

🔗 Link de Acesso Online

A aplicação está disponível para testes através do link: https://votacaov1-production.up.railway.app