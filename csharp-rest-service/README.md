# Introduction

csharp-rest-service provides you an out-of-the-box example of a .NET WebAPI application with PostgreSQL backend.

The application also includes several Steeltoe management endpoints (including a database health check) and dynamic log level management.

For more details on Steeltoe, visit <https://docs.steeltoe.io/>.

## Running the app locally

To run the sample application:

```script
dotnet run
```

## Database

You will need a local database running, see [DATABASE.md](DATABASE.md#local).

## Deploying to Kubernetes as a TAP workload with Tanzu CLI

If you make modifications to the source, push these changes to your own Git repository.

When you are done developing your app, you can simply deploy it using:

```script
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```script
tanzu apps workload create csharp-rest-service -f config/workload.yaml --local-path . --source-image <REPOSITORY-PREFIX>/csharp-rest-service-source --type web
```

## Accessing the app deployed to your cluster

Determine the URL to use for the accessing the app by running:

```script
tanzu apps workload get csharp-rest-service
```

To access the deployed app use the URL shown under "Workload Knative Services" and append the endpoint `/api/customer-profiles` to that URL.

This depends on the TAP installation having DNS configured for the Knative ingress.

## Inner loop experience with the app deployed to your Cluster

You may use TAP's Visual Studio plugin to enable inner loop development features including Live Update and Remote Debug.

You will have to update some fields in the root directory's `Tiltfile` to connect your live session to Kubernetes.

Update the `allow_k8s_contexts` line of the `Tiltfile` to indicate the Kubernetes context to use.

Update the `Tiltfile` or set the SOURCE_IMAGE environment variable to indicate the registry path where TAP should store your image.

```script
export SOURCE_IMAGE=registry/project/csharp-rest-service
export K8S_TEST_CONTEXT="a-kubernetes-context"
tilt up
tilt down
```
