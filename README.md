# Projeto Blog Pessoal - Backend

Este repositório tem como finalidade a implementação de uma API REST utilizando SpringBoot e MySQL para o projeto de blog pessoal do bootcamp da Generation Brasil.

## Spec

- SpringBoot 2.6.2;
- JDK 1.8.0_321; 
- Maven 3.8.4;
- MySQL Server 8.0.27.

## Modelagem

O banco de dados foi modelado conforme proposto no exercício de MySQL do bootcamp, que consiste em três tabelas seguindo um modelo simplificado de rede social.
As tabelas estão dispostas conforme a imagem abaixo: 

![DER model](/docs/ScreenShots/der_blogPessoal.PNG)


## Arquitetura

O projeto segue o padrão MVC distribuído entre os pacotes conforme imagem abaixo:

![packages](/docs/ScreenShots/packages.PNG)

## Endpoints

Utilizando o Postman ou acessando a url http://localhost:8080 é possível acessar os endpoints criados. Lembrando que para acessar o Swagger foi criado um usuário com nome e senha "admin" para poder ter acesso.<br>

- Usuário<br>

```/usuarios/cadastro``` : envia uma requisção POST para cadastrar um novo usuário no banco de dados;<br>

```/usuarios/logar``` : envia uma requisição POST para autenticar um usuário que consta no banco de dados;<br>

```/usuarios/todos``` : envia uma requisição GET para mostrar todos os usuários cadastrados;<br>

```/usuarios/{id}``` : envia uma requisição GET com o parâmetro "id" para buscar um usuário pelo seu Id;<br>

```/usuarios/pornome/{nome}``` : envia uma requisição GET com o parâmetro "nome" para buscar usuários pelo nome (ignora letras maiúsculas);<br>

```/usuarios/edit``` : envia uma requisição PUT com o parâmetro para editar dados de um usuário;<br>

```/usuarios/delete/{id}``` : envia uma requisição DELETE com o parâmetro "id" para deletar um usuário pelo seu Id.<br>

- Postagem<br>

```/postagem/todos``` : envia uma requisição GET para mostrar todas as postagens de todos os usuários;<br>

```/postagem/{id}``` : envia uma requisição GET com o parâmetro "id" para mostrar uma postagem pelo seu Id;<br>

```/postagem/titulo/{titulo}``` : envia uma requisição GET com o parâmetro "titulo" para mostrar todas as postagens que contenham o parâmetro inserido (ignora letras maiúsculas);<br>

```/postagem/save``` : envia uma requisição POST para salvar os dados da postagem no banco de dados;<br>

```/postagem/edit``` : envia uma requisição PUT para  editar os dados de uma postagem existente;<br>

```/postagem/delete/{id}``` : envia uma requisição DELETE com o parâmetro "id" para deletar uma postagem pelo seu Id.<br>

...continua
