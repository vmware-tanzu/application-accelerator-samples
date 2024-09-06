# tanzu-java-web-app

This is a sample of a Java Spring app for the Tanzu Platform for Kubernetes.

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
tanzu build --output-dir ./local
```

### Deploying the sample for TP for Kubernetes

Start the app deployment by running:

```sh
tanzu deploy --from-build ./local
```

### Scale the number of instances

Run this command to scale to 1 instance

```sh
tanzu app scale tanzu-java-web-app --instances=1
```