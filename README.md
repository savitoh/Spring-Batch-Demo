# Spring Batch Demo

Projeto desenvolvido para demonstrar funcionalidades básicas do framework Spring Batch. Utilizando a stack abaixo:

- Java 11
- Spring Boot v2.0.5.RELEASE
- Spring Batch 
- JDBC Template
- MySQL

## Tecnologias Necessárias

Abaixo segue a lista de tecnologias nescessárias para rodar o projeto.

* [JDK 11]
* [Maven v3.5+]
* [MySQL]  

Caso tenha dúvida de como instalar, recomendo procurar pelo tutorial especifico de cada tecnologia no repositório: [Como instalar XYZ].

## Rodando o projeto

Instruções para subir o projeto localmente.

Rode o seguinte script no MySQL para criar o Schema:

```sh
$ CREATE SCHEMA `batch-demo`;
```


Digite os comandos abaixo, através do terminal/cmd, acessando o diretório raiz do projeto:

```sh
$ mvn clean install
$ mvn spring-boot:run
```

[JDK 11]: <https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html>
[Maven v3.5+]: <https://maven.apache.org/download.cgi>
[Como Instalar XYZ]: <https://github.com/backend-br/como-instalar-xyz>
[MySQL]: <https://dev.mysql.com/downloads/>
