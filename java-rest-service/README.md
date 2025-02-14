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

<!--- #IF(#buildTool == 'maven') -->
```bash
./mvnw clean compile
```
<!--- #ELSE -->
```bash
./gradlew clean compileJava
```
<!--- #ENDIF -->

### Database

For local development a `docker compose` database instance will be started using `spring-boot-docker-compose`. 

### Run tests

After that it is a good habit to compile the test classes and execute those tests to see if your application is still behaving as you would expect:

<!--- #IF(#buildTool == 'maven') -->
```bash
./mvnw verify
```
<!--- #ELSE -->
```bash
./gradlew compileTestJava build
```
<!--- #ENDIF -->

### Start the app locally

Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)). In order 
to start the application a database instance should be running.

Launch application using a `docker-compose` database instance:

<!--- #IF(#buildTool == 'maven') -->
```bash
./mvnw spring-boot:run
```
<!--- #ELSE -->
```bash
./gradlew bootRun
```
<!--- #ENDIF -->

## Tanzu Platform Deployment

### Prerequisites

1. Access to [Tanzu Platform](https://docs.vmware.com/en/VMware-Tanzu-Platform/index.html) configured for platform builds.
1. The latest Tanzu CLI and plugins from `vmware-tanzu/app-developer` group installed. Installation instructions can be found in the Tanzu Platform documentation: [Before you begin](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/getting-started-deploy-app-to-space.html#before-you-begin-0).
1. You have access to a space for your project and you have used `tanzu login` to authenticate and configure your current Tanzu context and set your project and space using `tanzu project use` and `tanzu space use` respectively.

### About the ContainerApp

Change to the root directory of your generated app.

The project contains a `ContainerApp` manifest file that can be used when building and deploying the app. To review the content of this file run:

```sh
cat .tanzu/config/java-rest-service.yml
```

### Configure HTTP Ingress Routing

If you want to expose your application with a domain name and route traffic from the domain name to the deployed application, see [Adding HTTP Routing to an Application](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/how-to-ingress-to-app.html).

### Database

For deployments to Tanzu Platform the platform will provide a service instance and bind it to the running app. 

### Build and deploy the app

Change to the root directory of your generated app.

You can build and deploy the app with a single command.
Just run:

```sh
tanzu deploy
```

### Check the status of the service bound to the app

<!--- #IF(#databaseType == 'postgres') -->
The deployment includes a PostgreSQL instance using a provided service type.
The instance is bound to the app.

You can list the services created using:

```shell
tanzu services list
```

You can show the status of the service using:

```shell
tanzu services get PostgreSQLInstance/customer-profile
```
<!--- #ELSE -->
The deployment includes a MySQL instance using a provided service type.
The instance is bound to the app.

You can list the services created using:

```shell
tanzu services list
```

You can show the status of the service using:

```shell
tanzu services get MySQLInstance/customer-profile
```
<!--- #ENDIF -->

### Check the status of the app deployment

You can run this command to see the status of the app deployment:

```shell
tanzu apps get java-rest-service
```

### Use port-forward to access an app instance

You can use the `app port-forward` command to access your app instance's endpoint.
Just select the instance you want when prompted.
Use the following command to start the port-forward:

```shell
tanzu app port-forward java-rest-service --port 8080
```

Then you can access the app using http://localhost:8080.

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
