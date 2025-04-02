# MyFinances

**MyFinances** é uma aplicação fullstack desenvolvida para o gerenciamento de finanças pessoais, oferecendo uma API REST robusta com uma interface de usuário interativa.

## Funcionalidades

- **Gerenciamento de Lançamentos**: Registre e categorize receitas e despesas.
- **Autenticação Segura**: Utilize tokens JWT para autenticação e autorização.
- **Dashboard Interativo**: Visualize gráficos e relatórios financeiros.

## Tecnologias Utilizadas

### Backend

- **Spring Boot**: Framework principal para a construção da API REST.
- **Spring Data JPA**: Abstração para operações com o banco de dados.
- **Spring Security & JWT**: Implementação de autenticação e autorização com tokens JWT.
- **PostgreSQL**: Banco de dados relacional para armazenamento das informações.
- **Maven**: Gerenciador de dependências e automação de build.
- **JUnit & Mockito**: Testes unitários e de integração.

### Frontend

- **ReactJS**: Biblioteca para construção da interface do usuário.
- **Axios**: Cliente HTTP para requisições assíncronas.
- **Bootswatch**: Temas para o Bootstrap.
- **PrimeReact**: Biblioteca de componentes UI para React.
- **Context API**: Gerenciamento de estado global da aplicação.

## Estrutura do Projeto

O repositório está dividido em dois principais diretórios:

- **myfinances-api**: Contém o código-fonte da API desenvolvida com Spring Boot.
- **myfinances-app**: Contém o código-fonte da interface do usuário desenvolvida com ReactJS.

## Configuração e Execução

### Pré-requisitos

- **Java 8 ou superior**: Necessário para executar a API.
- **Node.js**: Necessário para executar o frontend React.
- **PostgreSQL**: Banco de dados para armazenar as informações.

### Backend

1. Clone o repositório:

   ```bash
   git clone https://github.com/williammian/myfinances.git

2. Navegue até o diretório da API:

   cd myfinances/myfinances-api
   
3. Configure o banco de dados PostgreSQL com as credenciais apropriadas.

4. Execute a aplicação Spring Boot:

   mvn spring-boot:run

### Frontend

1. Navegue até o diretório da interface do usuário:

   cd myfinances/myfinances-app

2. Instale as dependências:

   npm install

3. Execute a aplicação React:

   npm start
   
4. Acesse a aplicação no navegador em http://localhost:3000.

### Licença

   Este projeto está licenciado sob a MIT License.
