### SOBRE
Projeto de conclusão do curso IBM Blue Academy com parceria da Gama Academy.

Integrantes: Alan Frigério, Marlon Rocha, Viviani Andrade

https://www.gama.academy/


# BlueBank-api

O que foi utilizado:
Java 11
Spring Boot 2.5
JPA + Hibernate
H2 Database
Lombok
Maven

## Rodando o Projeto

A Base da API está na porta 8080

http://localhost:8080/bank

## Documentações da API:

Para o projeto, utilizamos o Swagger para facilitar a documentação dos endpoints

http://localhost:8080/bank/swagger-ui.html

## Banco de dados:

O Banco de dados é em memória, para faciltiar a apresentação do projeto.

http://localhost:8080/h2-console

Obs.: O H2 roda no perfil DEV. Caso queira mudar para PROD, só subir com o perfil, porém precisará colocar as variáveisd e ambiente de acordo com o application.yml

