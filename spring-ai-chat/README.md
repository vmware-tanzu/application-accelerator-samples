# Introduction spring-ai-chat

The spring-ai-chat app provides you an out-of-the-box application setup to fast start development of a Web Application for AI Chat based on [Spring AI](https://spring.io/projects/spring-ai).

This web application is using Spring AI to offer an interactive chat experience utilizing RAG (Retrieval Augmented Generation) to enable a user to ask questions about their own uploaded documents.

## Prerequisites
In order to further develop this application the following tools needs to be setup:
- Java Development Kit (https://bell-sw.com/)
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for the mentioned IDE

# Local

## Build
In order to compile the production code:

```sh
./mvnw clean compile
```

After that it is a good habit to compile the test classes and execute those tests to see if your application is still behaving as you would expect:

```sh
./mvnw verify
```

## Start and interact
Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)).

Set the `AI_API_KEY` environment variable with the API key to be used by your app:

```sh
export AI_API_KEY='<your-api-key>'
```

Launch application using default profile:

```sh
./mvnw spring-boot:run
```

### Accessing home page

You can access the public page at `http://localhost:8080/` by a web browser or using `curl`:

```sh
curl -L -H 'Content-Type: application/html' http://localhost:8080/
```

You'll be presented with a login page. You may login with either of the following sets
of credentials:

 - buzz / infinity
 - woody / bullseye

The security around the application is primarily so that each user will have their own,
distinct chat history and so that conversations with the LLM do not bleed into each
other.

# Deployment

## Dependencies
1. Tanzu CLI and the apps plugin v0.2.0 which are provided as part of [Tanzu Platform](https://docs.vmware.com/en/VMware-Tanzu-Platform/index.html). Installation instructions can be found at [ VMware Tanzu Platform Product Documentation - Before you begin](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/getting-started-deploy-app-to-space.html#before-you-begin-0).

2. You have access to a space for your project and you have used `tanzu login` to authenticate and configure your current Tanzu context and set your project and space using `tanzu project use` and `tanzu space use` respectively.

## Configuring your app environment

Change to the root directory of your generated app.
### Initialize the ContainerApp

```sh
tanzu app init
```
### Configure the JDK version

You need to specify the JDK version to be used for the app:

```sh
tanzu app config build non-secret-env set BP_JVM_VERSION=17
```

### Configure the AI API key

The application requires an AI API key to be provided.

You can set an environment variable for the app using this command:

```sh
tanzu app config non-secret-env set AI_API_KEY=<your-api-key>
```

> TODO: This needs to be set via a secret

### Configure HTTP Ingress Routing

If want to expose your application with a domain name and route traffic from the domain name to the deployed application, see [Adding HTTP Routing to an Application](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/how-to-ingress-to-app.html).


### Configure an image registry to use for the ContainerApp

```sh
tanzu build config --containerapp-registry REGISTRY
```

> Where `REGISTRY` is your container image registry location. For example, `my-registry.io/my-corp-apps/{name}` or `docker.io/<your-docker-id>/{name}` if you use Docker Hub. Note that use of literal `{name}` is required and it will be replaced with your apps name when you build.

## Deploying and building in one step

Change to the root directory of your generated app.

Run this command to build and deploy the app:

```sh
tanzu deploy
```

## Using separate build and deploy commands

Change to the root directory of your generated app.

### Building with local source

You can build using source from a locally cloned Git repository or from source on your local disk.

To build the app you can run this command:

```sh
tanzu build --output-dir ./build
```

### Deploying the sample for TP for Kubernetes

Start the app deployment by running:

```sh
tanzu deploy --from-build ./build
```

### Scale the number of instances

Run this command to scale to 1 instance

```sh
tanzu app scale hello-fun --instances=1
```

### PostgreSQL/pgvector

If you chose PostgreSQL/pgvector as your vector store, you'll need to create
a PostgresSQL database instance before deploying the application.

#### VMware Postgres Operator

> NOTE: This step would typically be performed by a platform or service operator.

You can use the VMware Postgres Operator with this app, just follow the the installation instructions from the [VMware Postgres Operator documentation](https://docs.vmware.com/en/VMware-SQL-with-Postgres-for-Kubernetes/2.3/vmware-postgres-k8s/GUID-install-operator.html).

#### Creating and configuring the Postgres database

If you installed the VMware Postgres Operator then you can simply create a `postgres.yaml` file with the following contents:

```yaml
apiVersion: sql.tanzu.vmware.com/v1
kind: Postgres
metadata:
  name: spring-ai-vector
spec:
  storageSize: 800M
  highAvailability:
    enabled: false
  imagePullSecret:
    name: registries-credentials
```

Once the `postgres.yaml` file is created you can apply it into your workload namespace using:

```sh
kubectl apply -n <workload-namespace> -f postgres.yaml
```
> NOTE: replace `<workload-namespace>` with the name of the namespace for your apps

Wait for the PostgreSQL instance to start up (status should go from `Created` to `Unavailable` to `Running`):

```sh
kubectl get postgres -w
```

Once the database is runnig, then run the following series of commands to add the `vector` extension:

1. Exec into the postgres pg-container pod container:
    ```sh
    kubectl exec -it -n <workload-namespace> -c pg-container spring-ai-vector-0 -- /bin/bash
    ```
1. Start `psql`:
    ```sh
    psql
    ```
1. Connect to the database:
    ```sql
    \connect spring-ai-vector
    ```
1. Check that the `vector` extensions is included in the available extensions:
    ```sql
    SELECT * FROM pg_available_extensions WHERE name='vector';
    ```
    If the "vector" extension isn't available, you won't be able to use this PostgreSQL
    database with the Spring AI Chat application.
1. Install the `vector` extension:
    ```sql
    CREATE EXTENSION IF NOT EXISTS "vector";
    ```
1. Verify that the extension has been created:
    ```sql
    SELECT * FROM pg_extension;
    ```
1. Exit `psql`:
    ```sql
    \q
    ```
1. Exit from the postgres pg-container:
    ```sh
    exit
    ```

#### Create the service binding

With the database configured we can finally bind it to our app.

Instructions TBD

# How to proceed from here?

Having the application locally running and deployed to a cluster you could add your domain logic, related persistence and new Spring MVC controllers.

Some tips:
- You can add images, additional CSS, etc to `src/main/resources/static` folder. It will be served by Spring Boot under `/static`. Those resources can be referenced to by Thymeleaf `@` character.
- In order to add a new page, create a new Controller, method and .html file in `src/main/resource/template` folder.

# References
- [Spring Boot](https://spring.io/projects/spring-boot/)
- [Spring AI](https://spring.io/projects/spring-ai)
- [Tanzu Application Platform](https://tanzu.vmware.com/application-platform)
- [Tanzu Developer Tools for Visual Studio Code](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.2/tap/GUID-vscode-extension-about.html)
- [Tanzu Developer Tools for IntelliJ](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.2/tap/GUID-intellij-extension-about.html)
- [Thymeleaf](https://www.thymeleaf.org/)
