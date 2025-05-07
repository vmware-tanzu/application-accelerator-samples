# tanzu-java-web-app

This is a sample of a Java Spring web app for the Tanzu Platform.

## Building and running the app locally

### Prerequisites
In order to further develop this application the following tools needs to be setup:
- Java Development Kit (https://bell-sw.com/libericajdk/)
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)

### Build the app

In order to compile the application code:

```sh
#IF(#buildTool == "gradle")
./gradlew build
#ENDIF
#IF(#buildTool == "maven")
./mvnw package
#ENDIF
```

### Run the app

```sh
#IF(#buildTool == "gradle")
./gradlew bootRun
#ENDIF
#IF(#buildTool == "maven")
./mvnw spring-boot:run
#ENDIF
```

### Accessing the running app

You can use [curl](https://curl.se/) command:

```sh
curl localhost:8080
```

Or, you can use [HTTPie](https://httpie.io/):

```sh
http -b :8080
```

You should see the following text:

```
Greetings from Spring Boot + Tanzu!
```

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

### Push the app

To push the app to your space, run this command:

```sh
cf push
```

This will deploy the app based on the settings in the `manifest.yml` file.

### Access the app

Find the route assigned to the app using this command:

```sh
cf app tanzu-java-web-app
```

The route assigned will be listed under `routes:`.

### Delete the app

To delete the app and remove the assigned route, run the following command:

```sh
cf delete tanzu-java-web-app -r
```
