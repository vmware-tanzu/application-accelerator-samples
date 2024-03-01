# Introduction java-server-side-ui
java-server-side-ui provides you an out-of-the-box application setup to fast start development of a Web Application based
on a service side rendering architecture.

It is leveraging Spring Boot as a technology stack, which provides:
- a way to implement Model, View and Controller(s) using Spring Web annotations and Thymeleaf template engine
- an Inversion of Control Container to wire together your classes at running without the need to write tightly-coupled code
- an integrated Web Server, so no need to deploy the built artifact to a separate running web or application server

The application contains example code to have a first public page. This page is built using
Thymeleaf and WebJars, so that common web libraries are available without the need to copy and paste them in the `resources/static` folder.

It provides a way to test the controller logic.

## Prerequisites
In order to further develop this application the following tools needs to be setup:
- Java Development Kit (https://bell-sw.com/)
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for the mentioned IDE

# Local
## Build
In order to compile the production code:
--- StartMaven
```bash
./mvnw clean compile
```
--- EndMaven

After that it is a good habit to compile the test classes and execute those tests to see if your application is still behaving as you would expect:
--- StartMaven
```bash
./mvnw verify
```
--- EndMaven

## Start and interact
Spring Boot has its own integrated Web Server (Apache Tomcat (https://tomcat.apache.org/)).

Launch application using default profile:
--- StartMaven
```bash
./mvnw spring-boot:run
```
--- EndMaven

### Accessing home page

You can access the public page at `http://localhost:8080/` by a web browser or using `curl`:

```bash
curl -X GET -H 'Content-Type: application/html' http://localhost:8080/
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

> The workload is set up by default to autoconfigure the actuators. This results in that the Spring Actuators are available at TCP port 8081 and will be used by Application Live View.
> Application Live View allows you see all health metrics in the TAP GUI. If you would like to have the Actuators available at TCP port 8080 you can set the
> annotation `apps.tanzu.vmware.com/auto-configure-actuators` to `false`.

### PostgreSQL/pgvector

If you chose PostgreSQL/pgvector as your vector store, you'll need to create
a PostgresSQL database instance before deploying the application.

Using the Tanzu CLI, start by adding the Tanzu Data Service package repository:

```bash
tanzu package repository add tanzu-data-service \
  --url registry.tanzu.vmware.com/packages-for-vmware-tanzu-data-services/tds-packages:1.13.0 \
  -n tap-install
```

Then install the PostgreSQL package:

```bash
tanzu package install postgres \
  --package postgres-operator.sql.tanzu.vmware.com \
  --version 2.3.0 \
  -n tap-install
```

Using `kubectl`, create a workloads namespace (if one does not yet exist):

```bash
kubectl create ns workloads
```

Then create a secret for the pull registry credentials (set the `REG_USERNAME` and `REG_PASSWORD` environment variables to your pull registry credentials or substitute them in the following command):

```bash
tanzu secret registry add pg-registry \
  --username $REG_USERNAME \
  --password $REG_PASSWORD \
  --server registry.tanzu.vmware.com \
  --export-to-all-namespaces \
  --yes \
  --namespace workloads
```

Create a `postgres.yaml` file with the following contents:

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
    name: pg-registry
```

Apply `postgres.yaml`:

```bash
kubectl apply -f postgres.yaml
```

Wait for the PostgreSQL instance to start up and then get the username/password
for the database by extracting them from the secret and running them through
Base64 decoding:

```bash
kubectl get secret spring-ai-vector-db-secret -o yaml
echo "<<base 64-encoded username>>" | base64 --decode
echo "<<base 64-encoded password>>" | base64 --decode
```

Create a port-forward from your local machine to the PostgreSQL service:

```sh
kubectl port-forward service/spring-ai-vector 5432:5432
```

Use `psql` to connect to the database:

```bash
psql -U $PG_USERNAME -p 5432 -h localhost -d spring-ai-vector
```

Within `psql`, view the available extensions:

```sql
SELECT * FROM pg_available_extensions;
```

You should see a "vector" extension included in the list of available extensions.
If the "vector" extension isn't available, you won't be able to use this PostgreSQL
database with the Spring AI Chat application.

Install the "vector" extension:

```sql
CREATE EXTENSION "vector";
```

Verify that the extension has been created:

```sql
SELECT * FROM pg_extension;
```

Finally, create a resource claim to the database with the name "vector-store"
(to match the name in `config/workload.yaml`):

```yaml
tanzu services resource-claims create vector-store --resource-api-version sql.tanzu.vmware.com/v1 --resource-kind Postgres --resource-namespace my-apps --resource-name spring-ai-vector
```

### Tanzu CLI
Using the Tanzu CLI one could apply the workload using the local sources:
```bash
tanzu apps workload apply \
  --file config/workload.yaml \
  --namespace <namespace> \
  --local-path . \
  --yes \
  --tail
````

> Change the namespace to where you would like to deploy this workload.

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
