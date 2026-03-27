🗳️ Sistema de Votação em Assembleia - API REST

Este projeto é uma solução robusta para gerenciamento de pautas e sessões de votação em assembleias. A aplicação permite cadastrar pautas, abrir sessões de votação com tempo determinado e registrar votos únicos de associados, garantindo a integridade e transparência do processo.

🛠️ Tecnologias Utilizadas

Java 21: Utilização de Records para DTOs imutáveis.
Spring Boot 3.3.0: Framework base para construção da API REST.
Spring Data JPA: Abstração de persistência com Hibernate.
PostgreSQL: Banco de dados relacional para persistência dos dados.
Docker & Docker Compose: Orquestração do ambiente de banco de dados.
JUnit 5 & Mockito: Testes unitários para garantir a qualidade das regras de negócio.
Maven: Gerenciamento de dependências e build.

🏗️ Arquitetura e Boas Práticas

O projeto foi construído seguindo princípios de Clean Code e padrões de projeto modernos:

Injeção de Dependência por Construtor: Garante a imutabilidade dos Services e facilita testes unitários.
Records (Java 21): Utilizados como DTOs para garantir que os dados trafegados na API sejam imutáveis e livres de efeitos colaterais.
Global Exception Handling: Centralização do tratamento de erros através de um @RestControllerAdvice, retornando mensagens claras e códigos HTTP semânticos.
Camada de Serviço Isolada: Toda a lógica de negócio reside nos Services, mantendo os Controllers (Resources) "magros" e focados apenas no protocolo HTTP.

🚀 Como Executar a Aplicação

Pré-requisitos

JDK 21 instalado.
Docker e Docker Compose instalados.
Maven 3.9+.

Passo 1: Subir o Banco de Dados

Na raiz do projeto, execute o comando para iniciar o container do PostgreSQL:

Criar o banco chamado votacaov1 no Postgres

Passo 2: Configurar o Perfil

Certifique-se de que o arquivo src/main/resources/application.properties está apontando para o banco local (localhost:5432).

Passo 3: Executar a Aplicação

mvn spring-boot:run

Passo 4: Executar os Testes

Para garantir que tudo está funcionando conforme o esperado:

mvn test

📖 Documentação dos Endpoints (API)
1.
2. Pautas
   POST /api/v1/pauta: Cadastra uma nova pauta.
   GET /api/v1/pauta/{id}/resultado: Retorna o fechamento da votação (Sim, Não e Status).

2. Sessões
   POST /api/v1/sessao/abrir: Abre uma pauta para votação.

   Regra: Se o campo duracaoMinutos não for enviado, a sessão fecha automaticamente em 1 minuto.

3. Votos
   POST /api/v1/voto: Registra o voto de um associado.

   Regra: Um associado só pode votar uma vez por pauta.
   O sistema impede votos em sessões já encerradas.

📝 Decisões de Projeto

Validação de Voto Único: Implementada tanto na camada de serviço (via votoRepository.associadoJaVotou) quanto no banco de dados através de uma Unique Constraint (associado + pauta), garantindo segurança em cenários de alta concorrência.
Tratamento de Datas: Utilização de LocalDateTime para garantir precisão no fechamento das sessões.
Relacionamentos: Mapeamentos @OneToOne e @ManyToOne configurados com FetchType.LAZY para otimizar o carregamento de dados e evitar o problema de N+1 consultas.

Nota: Este projeto foi desenvolvido visando performance e manutenibilidade, pronto para execução em ambientes de nuvem (Cloud).