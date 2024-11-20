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

### Prerequisites

1. Access to [Tanzu Platform](https://docs.vmware.com/en/VMware-Tanzu-Platform/index.html) configured for platform builds.
1. The latest Tanzu CLI and plugins from `vmware-tanzu/app-developer` group installed. Installation instructions can be found in the Tanzu Platform documentation: [Before you begin](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/getting-started-deploy-app-to-space.html#before-you-begin-0).
1. You have access to a space for your project and you have used `tanzu login` to authenticate and configure your current Tanzu context and set your project and space using `tanzu project use` and `tanzu space use` respectively.

### About the ContainerApp

Change to the root directory of your generated app.

The project contains a `ContainerApp` manifest file that can be used when building and deploying the app. To review the content of this file run:

```sh
cat .tanzu/config/tanzu-java-web-app.yml
```

### Configure HTTP Ingress Routing

If you want to expose your application with a domain name and route traffic from the domain name to the deployed application, see [Adding HTTP Routing to an Application](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/how-to-ingress-to-app.html).

### Building and deploying the app in one step

Change to the root directory of your generated app.

Run this command to build and deploy the app:

```sh
tanzu deploy
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

Then you can access the app using http://localhost:8080.
