# Microservice Gateway

## Status

![GitHub CI](https://github.com/butcherless/ms-gateway/workflows/CI/badge.svg)

Microservice Gateway is a Spring Cloud Gateway Server Web MVC application built with Spring Boot and Kotlin. It routes
country and airport requests to sibling microservices and adds an `X-Powered-By: MS-Gateway` response header.

## Tech stack

- Spring Boot 4.1.0
- Spring Cloud 2025.1.2
- Spring Cloud Gateway Server Web MVC 5.0.2
- Kotlin 2.4.0
- Java 25
- Maven 3.9.16 through the Maven Wrapper
- Apache Maven Build Cache Extension 1.2.3

## Prerequisites

- Java 25
- Git
- The country service running on port `8081`
- The airport service running on port `8082`

Maven does not need to be installed separately. Check the Java and Maven versions selected by the wrapper:

```bash
./mvnw --version
```

## Build and test

Run the complete build:

```bash
./mvnw clean verify
```

This compiles the application, runs the Spring application-context test, and creates the executable JAR under
`target/`.

## Run the gateway

Build and run the executable JAR:

```bash
./mvnw clean package
java -jar target/ms-gateway-0.0.1-SNAPSHOT.jar
```

For development, run it directly through Spring Boot:

```bash
./mvnw spring-boot:run
```

The gateway listens on port `8080`.

## Route configuration

| Incoming path   | Default upstream        | Forwarded path         |
|-----------------|-------------------------|------------------------|
| `/countries/**` | `http://localhost:8081` | `/ms-one/countries/**` |
| `/airports/**`  | `http://localhost:8082` | `/ms-two/airports/**`  |

Both routes add the following response header:

```text
X-Powered-By: MS-Gateway
```

The `GET_ROUTE_URI` environment variable overrides the upstream URI. The current configuration applies the same
override to both routes:

```bash
GET_ROUTE_URI=http://example.internal:8080 ./mvnw spring-boot:run
```

## HTTPie examples

The upstream services must be running before sending requests through the gateway.

### Countries

| Command                                         | Description                       |
|-------------------------------------------------|-----------------------------------|
| `http -v ':8080/countries'`                     | Retrieve countries sorted by name |
| `http -v ':8080/countries/es'`                  | Retrieve a country by its code    |
| `http -v ':8080/countries?name=Portugal'`       | Search for a country by name      |
| `http -v ':8080/countries?sortedBy=code'`       | Retrieve countries sorted by code |
| `http -v ':8080/countries/xy'`                  | Request a missing country         |
| `http -v ':8080/countries?name=MissingCountry'` | Search for a missing country      |
| `http -v ':8080/countries/xyz'`                 | Send an invalid country code      |

### Airports

| Command                                       | Description                                |
|-----------------------------------------------|--------------------------------------------|
| `http -v ':8080/airports'`                    | Retrieve airports sorted by name           |
| `http -v ':8080/airports?sortedBy=iata'`      | Retrieve airports sorted by IATA code      |
| `http -v ':8080/airports?sortedBy=icao'`      | Retrieve airports sorted by ICAO code      |
| `http -v ':8080/airports/iata/mad'`           | Retrieve an airport by its IATA code       |
| `http -v ':8080/airports/icao/lemd'`          | Retrieve an airport by its ICAO code       |
| `http -v ':8080/airports?name=Barajas'`       | Search airports by a partial name          |
| `http -v ':8080/airports?name=International'` | Search for multiple matching airport names |
| `http -v ':8080/airports/iata/xyz'`           | Request a missing IATA code                |
| `http -v ':8080/airports/icao/wxyz'`          | Request a missing ICAO code                |
| `http -v ':8080/airports/iata/xy'`            | Send an invalid IATA code                  |
| `http -v ':8080/airports/icao/xyz'`           | Send an invalid ICAO code                  |

## Useful commands

### List dependencies

```bash
./mvnw dependency:list -DincludeGroupIds=org.springframework
./mvnw dependency:list -DincludeGroupIds=org.jetbrains.kotlin
```

### Check dependency updates

```bash
./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-parent-updates
```

### Force all tests while retaining cache writes

Bypass existing cache entries, run the tests, and save the new result:

```bash
./mvnw clean verify -Dunit.test.skip=false -Dmaven.build.cache.skipCache=true
```

### Force all tests with caching disabled

Disable both cache reads and writes:

```bash
./mvnw clean verify -Dunit.test.skip=false -Dmaven.build.cache.enabled=false
```

### Maven build cache

A normal `clean verify` restores matching build results from the local cache when possible:

```bash
./mvnw clean verify
```

Local cache entries are stored under `~/.m2/build-cache`. The project retains up to three cached builds per artifact. CI
persists both `~/.m2/repository` and `~/.m2/build-cache`.

## Continuous integration

GitHub Actions runs the project on Java 25. The CI job:

1. Restores Maven dependencies and build-cache entries.
2. Runs `./mvnw -B -V clean verify`.
