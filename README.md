# Microservice Gateway

## Status

![Github CI](https://github.com/butcherless/ms-gateway/workflows/CI/badge.svg)

## Proof of concept and research with colleagues and friends.

Goals:

- _Gateway Service_
- Tinker with _Spring Cloud Gateway_

Tech stack:

- Spring Cloud 2023.x.x
- Spring Boot 3.2.x
- Kotlin 2.0.x
- Java 21

## New project basic structure

Check _Java_ and _Maven_ versions:

    ./mvnw -v

## Useful commands

Run the microservice:

    java -jar target/ms-gateway-0.0.1-SNAPSHOT.jar

Dependency list

    ./mvnw dependency:list -DincludeGroupIds=org.springframework
    ./mvnw dependency:list -DincludeGroupIds=org.jetbrains.kotlin

Dependency updates

    ./mvnw versions:display-dependency-updates

## HTTP client commands [`httpie`]

| Command                                   | Description                                           |
|-------------------------------------------|-------------------------------------------------------|
| `http -v ':8080/countries'`               | Retrieve all countries sorted by name (default limit) |
| `http -v ':8080/countries?sortedBy=code'` | Retrieve all countries sorted by code (default limit) |
| `http -v ':8080/countries/es'`            | Retrieve a country by its code                        |
| `http -v ':8080/countries?name=Portugal'` | Retrieve a country by its name                        |
| `http -v ':8080/countries/nf'`            | Get an HTTP Not Found response                        |
