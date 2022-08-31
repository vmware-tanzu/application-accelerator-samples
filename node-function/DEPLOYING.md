# Deploying

## Prerequisites

In order to further develop this application the following tools may be needed:
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for mentioned IDE
- Docker Desktop to execute integration tests or run the application locally
- [Curl](https://curl.se/download.html) for local testing
- [Pack CLI](https://buildpacks.io/docs/tools/pack/) for local testing

## Local

### Code Iteration without OCI Images

Use Node directly: 
```
npm install && npm start -- --log-level info
```

To test if it worked: `curl localhost:8080`

### Docker

Build your image and run it using Docker: 

```
pack build node-function --path . --buildpack paketo-buildpacks/nodejs --builder paketobuildpacks/builder:base
```

Where `node-function` is the name of your runnable function image.

Then run via Docker:

```
docker run -it --rm -p 8080:8080 node-function
```

Check for a successful response: `curl localhost:8080`

## Tanzu Application Platform (TAP)

Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.


### Deploying to Kubernetes as a TAP workload with Tanzu CLI

You need to select `Include TAP deployment resources` when generating the project for the steps below to work.

When you are done developing your function, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create node-function -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/node-function-source \
  --type web
```

### Interacting with Tanzu Application Platform

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get node-function
```

> NOTE: This depends on the TAP installation having DNS configured for the Knative ingress.

After deploying your function, you can interact with the function by using:

> NOTE: Replace the <URL> placeholder with the actual URL.

### Using Tilt with a Cluster

You may use [tilt](https://github.com/tilt-dev/tilt) `>v0.27.2` in combination with TAP's VS Code plugin to enable live development features including Application Live View and Live Update.

Update the `allow_k8s_contexts` line of the `Tiltfile` to indicate the Kubernetes context to use. 

Update the `Tiltfile` or set the SOURCE_IMAGE environment variable to indicate the registry path where TAP should store your image. 

```
export SOURCE_IMAGE=registry/project/node-function
export K8S_TEST_CONTEXT="a-kubernetes-context"
tilt up
tilt down
```
