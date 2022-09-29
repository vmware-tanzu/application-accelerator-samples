# Introduction rest-service-db
rest-service-db provides you an out-of-the-box application setup to implement your business logic. It is based on the
commonly known 3-layered application architecture in where the package `api` provides the presentation layer, `domain` provides 
the services and business domain and finally the `data` package provides you the capability to persist your domain.

It is leveraging Spring Boot as a technology stack, which provides:
- a way to implement REST(ful) API using Spring Web annotations
- generation of the OpenAPI definition based on your code
- data persistence using Spring Data JPA (now PostgreSQL is supported, but other databases can be easily added)
- an Inversion of Control Container to wire together your classes at running without the need to write tightly-coupled code

The application contains example code implementing REST API to write and read customer profile information to and from 
database. This example is intended to showcase best practices around using Spring Boot and it's libraries as well as
different types of tests which can be utilized to verify different parts of an application.

## Prerequisites
In order to further develop this application the following tools needs to be setup:
- Java Development Kit (https://bell-sw.com/)
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Docker Desktop to execute integration tests or run the application locally

# Local
## Build
In order to compile the production code:
--- StartMaven
```bash
./mvnw clean compile
```
--- EndMaven
--- StartGradle
```bash
./gradlew clean compileJava
```
--- EndGradle

After that it is a good habit to compile the test classes and execute those tests to see if your application is still behaving as you would expect:
--- StartMaven
```bash
./mvnw verify
```
--- EndMaven
--- StartGradle
```bash
./gradlew compileTestJava build
```
--- EndGradle

## Start and interact
Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)). In order 
to start the application a PostgreSQL instance should be running.

Launch application using profile `local`:
--- StartMaven
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```
--- EndMaven
--- StartGradle
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```
--- EndGradle

### OpenApi Definition
You can access the API docs using `curl`:

```bash
curl http://localhost:8080/api-docs  
```

### Create customer profile

You can access the `customer-profiles` API endpoint using `curl`:

```bash
curl -X POST -H 'Content-Type: application/json' http://localhost:8080/api/customer-profiles/ -d '{"firstName": "Joe", "lastName": "Doe", "email": "joe.doe@test.org"}'
```

### Get customer profile
Use the `id` received by previous POST call.
```bash
curl -X GET http://localhost:8080/api/customer-profiles/{id}
```

## Deploying to Azure Spring Apps with Azure CLI

Here, we will deploy the application on Azure Spring Apps, ensure that all prerequisites are met

Prerequisites:

* Completion of [Create Azure Spring Apps service instance](../SPRING_APPS.md)

### Create an Azure Database for Postgres

Using the Azure CLI, create an Azure Database for PostgreSQL

```shell
az postgres server create --name ${POSTGRES_SERVER} \
    --resource-group ${RESOURCE_GROUP} \
    --location ${REGION} \
    --admin-user ${POSTGRES_SERVER_USER} \
    --admin-password ${POSTGRES_SERVER_PASSWORD} \
    --yes

# Allow connections from other Azure Services
az postgres server firewall-rule create --name all-azure-ips \
     --server ${POSTGRES_SERVER} \
     --resource-group ${RESOURCE_GROUP} \
     --start-ip-address 0.0.0.0 --end-ip-address 0.0.0.0
```
> Note: The PostgreSQL Flexible Server will take 5-10 minutes to deploy

Create a database for the application:

```shell
export DB_NAME="development"

az postgres db create \
  --name ${DB_NAME} \
  --server-name ${POSTGRES_SERVER}
```

### Create the application in Azure Spring Apps

Create an application:

```shell
az spring app create --name ${SERVICE_APP} \
  --assign-endpoint true \
  --instance-count 1 \
  --memory 1Gi \
  --env "SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_SERVER}.postgres.database.azure.com:5432/${DB_NAME}" \
     "SPRING_DATASOURCE_USERNAME=${POSTGRES_SERVER_USER}@${POSTGRES_SERVER}" \
     "SPRING_DATASOURCE_PASSWORD=${POSTGRES_SERVER_PASSWORD}"
```
> Note: The app will take around 2-3 minutes to create.

### Build and Deploy the Application

Deploy and build the application, specifying its required parameters

```shell
az spring app deploy --name ${SERVICE_APP} \
   --source-path java-rest-service 
```
> Note: Deploying the application will take 5-10 minutes

### Test the Application

Run the following commands

```shell
export APP_URL=$(az spring app show --name ${SERVICE_APP} --query properties.url | tr -d '"')

curl "${APP_URL}/api-docs"
```

# How to proceed from here?
Having the application locally running and deployed to a cluster you could add your domain logic, related persistence and new RESTful controller.


