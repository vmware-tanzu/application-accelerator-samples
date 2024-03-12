# Introduction spring-ai-chat

The spring-ai-chat app provides you an out-of-the-box application setup to fast start development of a Web Application for a AI Chat based on [Spring AI](https://spring.io/projects/spring-ai).


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

## Tanzu Application Platform (TAP)
Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

> The workload is set up by default to auto configure the actuators. This results in that the Spring Actuators are available at TCP port 8081 and will be used by Application Live View.
> Application Live View allows you see all health metrics in the TAP GUI. If you would like to have the Actuators available at TCP port 8080 you can set the
> annotation `apps.tanzu.vmware.com/auto-configure-actuators` to `false`.

### Create a secret for the AI API key

The application requires an AI API key to be provided in a Secret resource named `ai-api`.
You can create the Secret using this command:

```sh
kubectl create secret generic ai-api --from-literal=api-key='<your-api-key>'
```

### PostgreSQL/pgvector

If you chose PostgreSQL/pgvector as your vector store, you'll need to create
a PostgresSQL database instance before deploying the application.

#### VMware Postgres Operator

You can use the VMware Postgres Operator with this app, just follow the the installation instructions from the [VMware Postgres Operator documentation](https://docs.vmware.com/en/VMware-SQL-with-Postgres-for-Kubernetes/2.3/vmware-postgres-k8s/GUID-install-operator.html).

Here is a cheat-sheet for installing in an existing TAP cluster that we have used for testing:

1. If you relocated your TAP installation packages then you also need to relocate the VMware Postgres Operator packages to the same registry following the docs to [Relocate Images to a Private Registry](https://docs.vmware.com/en/VMware-SQL-with-Postgres-for-Kubernetes/2.3/vmware-postgres-k8s/GUID-install-operator.html#relocate-images-to-a-private-registry).
1. Create a `data-services` namespace:
    ```sh
    kubectl create namespace data-services
    ```
1. Create an image-pull secret for this namespace (the credentials from your TAP install will be used to populate this secret)
    ```sh
    cat <<EOF | kubectl create -f -
    apiVersion: v1
    kind: Secret
    metadata:
      annotations:
        secretgen.carvel.dev/image-pull-secret: ""
      name: regsecret
      namespace: data-services
    type: kubernetes.io/dockerconfigjson
    data:
      .dockerconfigjson: e30K
    EOF
    ```
1. Install the package repository using this command (adjust the registry part of the image if you relocated the image):
    ```sh
    tanzu package repository add tanzu-data-services-repository \
      --url registry.tanzu.vmware.com/packages-for-vmware-tanzu-data-services/tds-packages:1.13.0 \
      --namespace data-services
    ```
1. Finally install the VMware Postgres Operator package:
    ```sh
    tanzu package install postgres \
      --package postgres-operator.sql.tanzu.vmware.com \
      --version 2.3.0 \
      --namespace data-services
    ```

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
    name: regsecret
```

Once the `postgres.yaml` file is created you can apply it into your workload namespace using:

```sh
kubectl apply -n <workload-namespace> -f postgres.yaml
```
> NOTE: replace `<workload-namespace>` with the name of the namespace for your apps

Wait for the PostgreSQL instance to start up and then run the following series of commands to add the `vector` extension:

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
    \connect spring-ai-vector;
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

#### Create the resource claim

With the database configured we can finally create a resource claim to the database with the name "vector-store"
to match what is already specified in the `workload.yaml`:

```sh
tanzu services resource-claims create vector-store \
  --resource-api-version sql.tanzu.vmware.com/v1 \
  --resource-kind Postgres \
  --resource-namespace <workload-namespace> \
  --resource-name spring-ai-vector
```
> NOTE: replace `<workload-namespace>` with the name of the namespace for your apps

### Deploy app using Tanzu CLI

Using the Tanzu CLI one could apply the workload using the local sources:

```bash
tanzu apps workload apply \
  --file config/workload.yaml \
  --namespace -n <workload-namespace> \
  --local-path . \
  --yes \
  --tail
```
> NOTE: replace `<workload-namespace>` with the name of the namespace for your apps

### Visual Studio Code Tanzu Plugin
When developing locally but would like to deploy the local code to the cluster, the Tanzu Plugin could help.
By using `Tanzu: Apply` on the `workload.yaml` it will create the Workload resource with the local source (pushed to an image registry) as
starting point.

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
