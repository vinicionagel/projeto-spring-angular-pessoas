

### 1) Back-end
A aplicação, a ser desenvolvida em Java, deverá expor uma API de cadastro, alteração, remoção e consulta de pessoas com as seguintes informações: 
 - Nome - obrigatório
 - Sexo
 - E-mail - não obrigatório, deve ser validado caso preenchido
 - Data de Nascimento - obrigatório, deve ser validada
 - Naturalidade
 - Nacionalidade
 - CPF - obrigatório, deve ser validado (formato e não pode haver dois cadastros com mesmo cpf)

Obs: a data de cadastro e atualização dos dados devem ser armazenados.

### 2) Front-end
A aplicação deverá ser acessível via navegador e possuir uma tela com formulário.
Não há restrição em relação à tecnologia para o desenvolvimento do frontend.

### 3) Segurança
O acesso à aplicação só poderá ser realizado por um usuário pré-existente via autenticação **basic**.

### 4) Instalação
A aplicação deverá estar disponível em uma imagem docker a partir do **docker-hub** e não deve exigir configurações/parâmetros. Ou seja, ao rodar a imagem, deve levantar a aplicação e funcionar.

### 5) Código fonte
A aplicação deverá possuir um endpoint **/source** acessível sem autenticação via **HTTP GET** que deverá retornar o link do projeto no github com o código fonte do projeto desenvolvido.

## Extras
1) A aplicação rodando em algum ambiente em nuvem.
2) Teste de integração da API em linguagem de sua preferência (Damos importância para pirâmide de testes)
3) A API desenvolvida em REST, seguindo o modelo de maturidade de Richardson ou utilizando GraphQL.
4) A API deverá conter documentação executável que poderá ser utilizada durante seu desenvolvimento. (Utilizar swagger)
5) Integração com OAuth 2 Google (https://developers.google.com/identity/protocols/OAuth2)
6) Implementar Chat entre as pessoas que estão na aplicação
7) Versão 2 da API que deve incluir endereço da pessoa como dado obrigatório. Versão 1 deve continuar funcionando. 


## Executando a aplicação...

OBS: para parte do back-end é necessário ter o banco, no compose já sobe todos os conteiners.

Subir só o back-end... 

```shell script
mvn spring-boot:run 
```

<h3>Para executar a suíte de testes desenvolvida:<h3>

```shell script
mvn clean test
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v1/pessoas
```

<h3>Para subir a aplicação<h3>

```
docker-compose up
```

Obs: Com isso já sobe toda a parte de banco de dados, front e api.
Front acesso: localhost:4200

<h3> Usuario e senha da API: </h3>

```
Usuario:admin
Senha:admin
```

<h3> Documentação da API: </h3>

http://localhost:8080/swagger-ui.html#/



