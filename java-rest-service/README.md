# Introduction java-rest-service

The java-rest-service app provides you an out-of-the-box application setup to implement your business logic.
It is based on the commonly known 3-layered application architecture in where the package `api` provides the
presentation layer, `domain` provides the services and business domain and finally the `data` package provides
you the capability to persist your domain.

It is leveraging Spring Boot as a technology stack, which provides:
- a way to implement REST(ful) API using Spring Web annotations
- generation of the OpenAPI definition based on your code
- data persistence using Spring Data JPA (now PostgreSQL and MySQL is supported, but other databases can be easily added)
- an Inversion of Control Container to wire together your classes at running without the need to write tightly-coupled code

The application contains example code implementing REST API to write and read customer profile information to and from 
database. This example is intended to showcase best practices around using Spring Boot and it's libraries as well as
different types of tests which can be utilized to verify different parts of an application.

## Prerequisites

In order to further develop this application the following tools needs to be setup:
- Java Development Kit
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Docker Desktop to execute integration tests or run the application locally

## Local

### Build

In order to compile the production code:

<!-- #IF(#buildTool == 'maven') -->
```bash
./mvnw clean compile
```
<!-- #ELSE -->
```bash
./gradlew clean compileJava
```
<!-- #ENDIF -->

### Database

For local development a `docker compose` database instance will be started using `spring-boot-docker-compose`. 

### Run tests

After that it is a good habit to compile the test classes and execute those tests to see if your application is still behaving as you would expect:

<!-- #IF(#buildTool == 'maven') -->
```bash
./mvnw verify
```
<!-- #ELSE -->
```bash
./gradlew compileTestJava build
```
<!-- #ENDIF -->

### Start the app locally

Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)). In order 
to start the application a database instance should be running.

Launch application using a `docker-compose` database instance:

<!-- #IF(#buildTool == 'maven') -->
```bash
./mvnw spring-boot:run
```
<!-- #ELSE -->
```bash
./gradlew bootRun
```
<!-- #ENDIF -->

## Tanzu Platform Deployment

*TBD*

## Intercting with the running app

### OpenApi Definition

Set the env var `APP_URL` to the current URL you are using, e.g. `http://localhost:8080` when running a local server or using port-forward for app deployed to Tanzu Platform.

```bash
export APP_URL=http://localhost:8080
```

You can access the API docs using `curl`:

```bash
curl $APP_URL/api-docs  
```

### Create customer profile

You can access the `customer-profiles` API endpoint using `curl`:

```bash
curl -X POST -H 'Content-Type: application/json' $APP_URL/api/customer-profiles -d '{"firstName": "Joe", "lastName": "Doe", "email": "joe.doe@test.org"}'
```

### Get customer profile

Use the `id` received by previous POST call.
```bash
curl -X GET $APP_URL/api/customer-profiles/{id}
```

### Get all customer profiles

```bash
curl -X GET $APP_URL/api/customer-profiles/
```

### Update customer profile

Use the `id` received by previous creation call.
```bash
curl -X PATCH -H 'Content-Type: application/json' $APP_URL/api/customer-profiles/{id} -d '{"firstName": "Jane", "lastName": "Little"}'
```

### Delete customer profile

Use the `id` received by previous creation call.
```bash
curl -X DELETE $APP_URL/api/customer-profiles/{id}
```

## How to proceed from here?

Having the application locally running and deployed to the platform, you could add your domain logic, related persistence and new RESTful controller.
