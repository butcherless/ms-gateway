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

## HTTP client commands [`httpie`] for countries

| Command                                         | Description                                           |
|-------------------------------------------------|-------------------------------------------------------|
| `http -v ':8080/countries'`                     | Retrieve all countries sorted by name (default limit) |
| `http -v ':8080/countries/es'`                  | Retrieve a country by its code                        |
| `http -v ':8080/countries?name=Portugal'`       | Retrieve a country by its name                        |
| `http -v ':8080/countries?sortedBy=code'`       | Retrieve all countries sorted by code (default limit) |
| `http -v ':8080/countries/xy'`                  | Get an HTTP Not Found response for missing country    |
| `http -v ':8080/countries?name=MissingCountry'` | Get an HTTP Not Found response for missing country    |
| `http -v ':8080/countries/xyz'`                 | Get an HTTP Bad Request response                      |

## HTTP client commands [`httpie`] for airports

| Command                                       | Description                                               |
|-----------------------------------------------|-----------------------------------------------------------|
| `http -v ':8080/airports'`                    | Retrieve all airports sorted by name (default limit)      |
| `http -v ':8080/airports/iata/mad'`           | Retrieve an airport by its IATA code                      |
| `http -v ':8080/airports/icao/lemd'`          | Retrieve an airport by its ICAO code                      |
| `http -v ':8080/airports?name=Barajas'`       | Retrieve all airports containing the name                 |
| `http -v ':8080/airports?name=International'` | Retrieve all airports containing the name                 |
| `http -v ':8080/airports?sortedBy=iata'`      | Retrieve all airports sorted by iata code (default limit) |
| `http -v ':8080/airports?sortedBy=icao'`      | Retrieve all airports sorted by icao code (default limit) |
| `http -v ':8080/airports/iata/xyz'`           | Get an HTTP Not Found response for missing airport        |
| `http -v ':8080/airports/icao/wxyz'`          | Get an HTTP Not Found response for missing airport        |
| `http -v ':8080/airports/iata/xy'`            | Get an HTTP Bad Request response                          |
| `http -v ':8080/airports/icao/xyz'`           | Get an HTTP Bad Request response                          |
