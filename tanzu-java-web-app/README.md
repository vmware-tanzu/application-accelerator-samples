# tanzu-java-web-app

This is a sample of a Java Spring web app for the Tanzu Platform for Kubernetes.

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

*TBD*
