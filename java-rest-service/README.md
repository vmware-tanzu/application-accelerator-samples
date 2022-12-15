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

## Database

You will need a local database running, see [DATABASE.md](DATABASE.md#local).

## Start and interact

Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)). In order 
to start the application a database instance should be running.

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

### Get all customer profiles

```bash
curl -X GET http://localhost:8080/api/customer-profiles/
```

### Update customer profile

Use the `id` received by previous creation call.
```bash
curl -X PATCH -H 'Content-Type: application/json' http://localhost:8080/api/customer-profiles/{id} -d '{"firstName": "Jane", "lastName": "Little"}'
```

### Delete customer profile

Use the `id` received by previous creation call.
```bash
curl -X DELETE http://localhost:8080/api/customer-profiles/{id}

## Deploying to Azure Spring Apps with Azure CLI

Here, we will deploy the application on Azure Spring Apps, ensure that all prerequisites are met

Prerequisites:

* Completion of [Create Azure Spring Apps service instance](https://github.com/Azure-Samples/acme-fitness-store/blob/Azure/README.md#create-azure-spring-apps-service-instance)

### Create an Azure Database for PostgreSQL

Using the Azure CLI, create an Azure Database for PostgreSQL

```shell
az postgres flexible-server create --name db-server \
    --resource-group ${RESOURCE_GROUP} \
    --location ${REGION} \
    --admin-user ${DB_SERVER_USER} \
    --admin-password ${DB_SERVER_PASSWORD} \
    --public-access 0.0.0.0 \
    --tier Burstable \
    --sku-name Standard_B1ms \
    --version 14 \
    --storage-size 32 \
    --yes

```
> Note: The PostgreSQL Flexible Server will take 5-10 minutes to deploy

Create a database for the application:

```shell
export DB_NAME="development"

az postgres flexible-server db create \
  --name customer_profile \
  --server-name db-server
```

### Create the application in Azure Spring Apps

Create an application:

```shell
az spring app create --name app-name \
  --assign-endpoint true \
  --instance-count 1 \
  --memory 1Gi 
```
> Note: The app will take around 2-3 minutes to create.

Create a Service Connector for the Application in order to access the Postgres Database:

```shell
az spring connection create postgres-flexible \
  --resource-group ${RESOURCE_GROUP} \
  --service ${ASA_INSTANCE} \
  --app app-name \
  --tg ${RESOURCE_GROUP} \
  --server db-server \
  --database customer_profile \
  --client-type springboot \
  --secret name=${POSTGRES_SERVER_USER} secret=${POSTGRES_SERVER_PASSWORD}
```

--- StartACS
### Enable External Configuration

Define Git Repository in Application Configuration Service:

```shell
    az spring application-configuration-service git repo add \
      --name app-config-name \
      --label acs-repo-label \
      --patterns acs-repo-pattern \
      --uri acs-repo-uri 
```

Bind the application to Application Configuration Service to enable external configuration loading:

```shell
    az spring application-configuration-service bind --app app-name
```

Configure the config file patterns for the application:

```shell
    az spring app update --config-file-patterns acs-repo-pattern
```
--- EndACS
### Build and Deploy the Application

Deploy and build the application, specifying its required parameters

```shell
az spring app deploy --name app-name \
   --build-env BP_JVM_VERSION=java-version \
   --source-path java-rest-service 
```
> Note: Deploying the application will take 5-10 minutes

### Test the Application

Run the following commands

```shell
export APP_URL=$(az spring app show --name app-name --query properties.url | tr -d '"')

curl "${APP_URL}/api-docs"
```

# How to proceed from here?
Having the application locally running and deployed to a cluster you could add your domain logic, related persistence and new RESTful controller.


