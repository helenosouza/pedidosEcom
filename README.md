# PedidosEcom

## Visão Geral

**pedidosEcom** é uma aplicação web para gerenciamento de pedidos em ambiente de e-commerce. Desenvolvido utilizando as tecnologias modernas do ecossistema Spring, o projeto oferece uma arquitetura robusta e escalável, com suporte a segurança, persistência de dados e gerenciamento de migrações de banco de dados.

## Funcionalidades

- **Gerenciamento de Pedidos:** Criação, atualização e consulta de pedidos.
- **Segurança:** Implementação de autenticação e autorização utilizando Spring Security e JSON Web Tokens (JWT).
- **Persistência de Dados:** Integração com banco de dados MySQL através do Spring Data JPA.
- **Migração de Banco de Dados:** Controle de versões do esquema de dados com Liquibase.

## Estrutura do Projeto

A organização dos diretórios segue boas práticas de desenvolvimento com Spring Boot:

- **src/main/java:** Código fonte da aplicação, estruturado em pacotes:
  - **config:** Configurações gerais da aplicação.
  - **controller:** Controladores que gerenciam as requisições HTTP.
  - **dto:** Objetos de transferência de dados entre as camadas.
  - **entity:** Entidades que representam o modelo de domínio e mapeiam o banco de dados.
  - **enums:** Enumerações utilizadas no sistema.
  - **errors:** Tratamento de erros e exceções.
  - **repository:** Repositórios para acesso aos dados.
  - **security:** Configurações e implementações de segurança.
  - **service:** Lógica de negócio da aplicação.
  - **utils:** Classes utilitárias e helpers.
- **src/main/resources:** Arquivos de configuração, templates, recursos estáticos e scripts do Liquibase para controle do banco de dados.

## Arquitetura e Padrões de Projeto

A aplicação **pedidosEcom** foi projetada com foco na modularidade, escalabilidade e na manutenção facilitada, adotando os seguintes princípios e padrões:

### Arquitetura em Camadas

- **Camada de Apresentação (Controller):** Responsável por receber e tratar as requisições HTTP, delegando as chamadas para a camada de serviços. Essa separação permite que a lógica de negócio permaneça isolada da lógica de apresentação.
  
- **Camada de Serviço (Service):** Contém a lógica de negócio da aplicação. Os serviços processam os dados e interagem com os repositórios para persistência, garantindo uma separação clara de responsabilidades.

- **Camada de Persistência (Repository):** Utiliza o Spring Data JPA para abstrair o acesso ao banco de dados, implementando o padrão Repository para facilitar operações de CRUD e consultas customizadas.

- **Camada de Transferência de Dados (DTO):** Os Data Transfer Objects são usados para transportar dados entre as camadas, evitando o acoplamento direto entre as entidades de domínio e a interface de comunicação com o usuário.

### Padrões de Projeto Adotados

- **Dependency Injection:** Fornecido pelo Spring Framework, este padrão desacopla as classes e promove uma maior testabilidade e facilidade na manutenção, permitindo a injeção de dependências em tempo de execução.

- **Repository Pattern:** Abstração do acesso aos dados através do Spring Data JPA, facilitando a manipulação e a persistência das entidades sem expor os detalhes de implementação do banco de dados.

- **DTO Pattern:** Utilização de objetos de transferência de dados (DTOs) para encapsular as informações trafegadas entre as camadas, melhorando a segurança e a performance ao evitar o acoplamento com as entidades de domínio.

- **Model-View-Controller (MVC):** Padrão utilizado na camada de apresentação que separa a lógica de interface do usuário (View), o controle de fluxo (Controller) e a lógica de negócios (Model), promovendo um desenvolvimento mais organizado e manutenível.

- **Padrões Complementares:** A arquitetura também pode se beneficiar, de forma implícita, de padrões como Singleton (na gestão de componentes de configuração e serviços gerenciados pelo Spring) e Factory (na criação e gerenciamento de instâncias quando necessário), ambos facilitados pelo container de Inversão de Controle (IoC) do Spring.

### Outras Considerações Arquiteturais

- **Migração e Versionamento do Banco de Dados:** O uso do Liquibase permite o versionamento do esquema do banco de dados, garantindo que todas as alterações sejam aplicadas de forma controlada e reproduzível.
  
- **Escalabilidade e Modularidade:** A estrutura e os padrões adotados possibilitam uma evolução incremental do sistema, facilitando a adição de novas funcionalidades sem comprometer a integridade do código.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security**
- **Liquibase**
- **MySQL**
- **Lombok**
- **Maven**

## Configuração e Execução

### Pré-requisitos

- **Java:** Ter Java 21 ou superior instalado.
- **IDE Java:** Você pode utilizar qualquer IDE de sua preferência, sendo o IntelliJ uma opção altamente recomendada.
- **Maven:** Certifique-se de que o Maven está instalado.
- **Docker e Docker Compose:** O projeto utiliza o Docker Compose para criar uma imagem do MySQL 8, que será automaticamente configurada e iniciada para suportar as migrações do Liquibase.

### Passos para Execução

1. **Clone o repositório:**

   ```bash
   git clone do repositorio
   ```

2. **Abra o projeto na sua IDE de preferência:**

   - Recomendo o IntelliJ, mas os processos podem ser efetuados em qualquer IDE Java.

3. **Compile e instale as dependências:**

   Execute o comando abaixo para compilar o projeto e instalar as dependências. **Atenção:** Ao rodar o comando, utilize a flag para pular os testes (por exemplo, `-DskipTests`):

   ```bash
   mvn clean install -DskipTests
   ```

4. **Docker Compose:**

   Certifique-se de ter o Docker e o Docker Compose instalados. Ao iniciar o projeto, o Docker Compose irá:
   
   - Criar e configurar uma imagem do MySQL 8.
   - Criar a base de dados necessária para que o Liquibase execute as migrations automaticamente, sem a necessidade de modificar as configurações no arquivo de propriedades.

5. **Inicialização da Aplicação:**

   Após a conclusão dos passos anteriores, execute a aplicação.

## Documentação

- Você pode conferir a documentação e rotas pelo link [(ACESSAR DOCUMENTAÇÃO)](https://documenter.getpostman.com/view/7314437/2sAYk8v3qq) lembrando que todos os usuarios de teste possuem a senha padrao 123456
