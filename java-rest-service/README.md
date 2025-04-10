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

### Set env var with app URL

Set the env var `APP_URL` to the current URL you are using, e.g. `http://localhost:8080` when running a local server.

```bash
export APP_URL=http://localhost:8080
```

This can be used when interacting with the app as described in [Interacting with the running app](#interacting-with-the-running-app)

## Tanzu Platform deployment

### Prerequisites

You need to be logged in to Tanzu Platform for Cloud Foundry and have set the target org and space.

### Build the app

To compile the application code and create a runnable jar file, use the following command:

```sh
#IF(#buildTool == "gradle")
./gradlew build
#ENDIF
#IF(#buildTool == "maven")
./mvnw package
#ENDIF
```

### Create a service instance

We will take advantage of service available in the marketplace.
To review what services are available, run the following command:

```sh
cf marketplace
```

Based on the offerings and plans available, you might have to adjust the command below.

<!-- #IF(#databaseType == 'postgres') -->
#### Using PostgreSQL database

To create a PostgreSQL service, run the following command:

```sh
cf create-service postgres on-demand-postgres-db customer-database
```
<!-- #ENDIF -->
<!-- #IF(#databaseType == 'mysql') -->
#### Using MySQL database

To create a MySQL service, run the following command:

```sh
cf create-service p.mysql db-small customer-database
```
<!-- #ENDIF -->

### Push the app

To push the app to your space, run this command:

```sh
cf push
```

This will deploy the app based on the settings in the `manifest.yml` file, including binding to a service named `customer-database`.

### Access the app

Find the route assigned to the app using this command:

```sh
cf app rest-service-db
```

The route assigned will be listed under `routes:`.

### Set env var with app URL

Set the env var `APP_URL` to the assigned URL from previous step.

```bash
export APP_URL=http://<assigned-route>
```

This can be used when interacting with the app as described in [Interacting with the running app](#interacting-with-the-running-app)

### Delete the app

To delete the app and remove the assigned route, run the following command:

```sh
cf delete rest-service-db -r
```
<!-- #IF(!(#persistenceType == 'jpa' && #databaseType == 'h2')) -->

To delete the service, run this command:

```sh
cf delete-service customer-database
```
<!-- #ENDIF -->

## Interacting with the running app

### OpenApi Definition

The env var `APP_URL` should be set to the current URL you are using, e.g. `http://localhost:8080` when running a local server or the assigned route prefixed with `http://`.

```bash
export APP_URL=<url-for-running-app>
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
