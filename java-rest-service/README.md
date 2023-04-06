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
- Tanzu Developer Tools plugin for mentioned IDE
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

## Database

If you are using Testcontainers for testing then you will need a local database running, see [DATABASE.md](DATABASE.md#local).
If you selected to use H2 as an in-memory database for testing then you don't need to worry, it is included already.

## Run tests

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

Set the env var `APP_URL` to the current URL you are using, e.g. `http://localhost:8080` when running a local server or the URL for the service when runing as a cloud workload.

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

# Cluster Deployment

## Database

You will need a database running in yur cluster, see [DATABASE.md](DATABASE.md#kubernetes).

## Tanzu Application Platform (TAP)

Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

> The workload is set up by default to autoconfigure the actuators. This results in that the Spring Actuators are available at TCP port 8081 and will be used by Application Live View.
> Application Live View allows you see all health metrics in the TAP GUI. If you would like to have the Actuators available at TCP port 8080 you can set the
> annotation `apps.tanzu.vmware.com/auto-configure-actuators` to `false`.

### Test Pipeline

Before deploying your application a Tekton Pipeline responsible for the testing step needs to be available in your application
namespace. If your Namespace Provisioner includes a test pipeline for Java then you can rely on that. If not, then you can install one using one provided as part of the [Namespace Provisioner poyglot sample](https://raw.githubusercontent.com/vmware-tanzu/application-accelerator-samples/main/ns-provisioner-samples/testing-scanning-supplychain-polyglot/tekton-pipeline-java.yaml).

> **NOTE:** if you need support for TestContainers, an alternative pipeline definition to use is available in the Application Accelerators Samples [java-rest-service sample](https://raw.githubusercontent.com/vmware-tanzu/application-accelerator-samples/main/java-rest-service/config/testcontainers-test-pipeline.yaml).

### Tanzu CLI

Using the Tanzu CLI one could apply the workload using the local sources:
```bash
tanzu apps workload apply \
  --file config/workload.yaml \
  --namespace <workload-namespace> \
  --source-image <image-registry> \
  --local-path . \
  --yes \
  --tail
```

Note: change the namespace to where you would like to deploy this workload. Also define the (private) image registry you
are allowed to push the source-image, like: `docker.io/username/repository`.

### Visual Studio Code Tanzu Plugin

When developing local but would like to deploy the local code to the cluster the Tanzu Plugin could help.
By using `Tanzu: Apply` on the `workload.yaml` it will create the Workload resource with the local source (pushed to an image registry) as
starting point.

# How to proceed from here?

Having the application locally running and deployed to a cluster you could add your domain logic, related persistence and new RESTful controller.
