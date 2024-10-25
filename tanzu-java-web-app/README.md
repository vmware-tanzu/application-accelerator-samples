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

## Configuring your Tanzu Platform build environment

### Prerequisites

1. Tanzu CLI and the apps plugin v0.2.0 which are provided as part of [Tanzu Platform](https://docs.vmware.com/en/VMware-Tanzu-Platform/index.html). Installation instructions can be found at [VMware Tanzu Platform Product Documentation - Before you begin](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/getting-started-deploy-app-to-space.html#before-you-begin-0).

2. You have access to a space for your project, and you have used `tanzu login` to authenticate and configure your current Tanzu context, and you have set your project and space using `tanzu project use` and `tanzu space use` respectively.

### Configure an image registry to use for building the app

Before you can build your app, you need to specify the registry where the resulting image from the build can be stored.

```sh
tanzu build config --containerapp-registry REGISTRY
```

> Where `REGISTRY` is your container image registry location. For example, `my-registry.io/my-corp-apps/{name}` or `docker.io/<your-docker-id>/{name}` if you use Docker Hub. Note that use of literal `{name}` is required, and it will be replaced with your apps name when you build.

## Configuring your app environment

Change to the root directory of your generated app.

### About the ContainerApp

The project contains a `ContainerApp` manifest file that can be used when building and deploying the app. To review the content of this file run:

```sh
cat .tanzu/config/tanzu-java-web-app.yml
```

### Configure for native build with GraalVM

If you would like the build to use GraalVM for compipling native image then use the following comands:

```sh
tanzu app config build non-secret-env set BP_NATIVE_IMAGE=true
tanzu app config build non-secret-env set BP_MAVEN_ACTIVE_PROFILES=native
```

### Configure HTTP Ingress Routing

If you want to expose your application with a domain name and route traffic from the domain name to the deployed application, see [Adding HTTP Routing to an Application](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/how-to-ingress-to-app.html).

### Building and deploying the app to Tanzu Platform for Kubernetes in one step

Change to the root directory of your generated app.

Run this command to build and deploy the app:

```sh
tanzu deploy
```

### Using separate build and deploy commands

Change to the root directory of your generated app.

#### Building from local source

You can build using source on your local disk.

To build the app you can run this command:

```sh
tanzu build --output-dir ./prebuilt
```

#### Deploying the app to Tanzu Platform for Kubernetes

Start the app deployment by running:

```sh
tanzu deploy --from-build ./prebuilt
```

### Scale the number of instances

Check the number of instances of you app using:

```sh
tanzu app instance tanzu-java-web-app
```

Run this command to scale to 2 instances:

```sh
tanzu app scale tanzu-java-web-app --instances=2
```

### Use port-forward to access an app instance

You can use the `app port-forward` command to access your app instance's endpoint.
Just select the instance you want when prompted.
Use the following command to start the port-forward:

```sh
tanzu app port-forward tanzu-java-web-app --port 8080
```
