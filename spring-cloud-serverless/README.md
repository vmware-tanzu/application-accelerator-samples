# spring-cloud-serverless repo

This repo provides a simple serverless Hello web app based on Spring Boot and Spring Cloud Function.

It can be deployed as a standalone web app or as a Kubernetes Deployment and Service or on Azure Spring Apps.

## The code

> **NOTE**: The project is configured for Java 11, if you prefer a different version, then modify the `java.version` property in `pom.xml`.

The project contains the following Function bean definition:

```text
	@Bean
	public Function<String, String> hello() {
		return (in) -> {
			return "Hello " + in;
		};
	}
```

This simple serverless app returns the input value, prefixed with "Hello ". This is just a simple example what a Spring Cloud Function app can do. 
It is defined in `src/main/java/com/example/helloapp/HelloAppApplication.java`

### Standalone app with embedded Tomcat server

You can build the project using Maven:

```bash
mvn clean package
```

To run the app using the embedded Tomcat server you can run this command:

```bash
mvn spring-boot:run
```

You can access the app using `curl`:

```bash
curl -w'\n' -H 'Content-Type: text/plain' localhost:8080 -d "Fun"
```

## Deploying to Azure Spring Apps with Azure CLI

Here, we will deploy the application on Azure Spring Apps, ensure that all prerequisites are met

Prerequisites:

* Completion of [Create Azure Spring Apps service instance](../SPRING_APPS.md)

### Create the application in Azure Spring Apps

Create an application:

```shell
az spring app create --name ${SERVICE_APP} \
  --assign-endpoint true \
  --instance-count 1 \
  --memory 1Gi
```
> Note: The app will take around 2-3 minutes to create.

### Build and Deploy the Application

Deploy and build the application, specifying its required parameters

```shell
az spring app deploy --name ${SERVICE_APP} \
    --source-path spring-cloud-serverless
```
> Note: Deploying the application will take 5-10 minutes

### Test the Application

Run the following commands

```shell
export APP_URL=$(az spring app show --name ${SERVICE_APP} --query properties.url | tr -d '"')

curl "${APP_URL}"
```

