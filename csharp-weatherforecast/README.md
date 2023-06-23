# C# Sample Accelerator

A sample accelerator for C#.

This sample is the Weather Forecast RESTful API application made available from Microsoft.

The application also uses [Steeltoe](https://steeltoe.io) to configure the application to listen on ports specified by the hosting environment and provide dynamic logging and application management endpoints.

The starting source for this sample was created using:

```
dotnet new webapi --framework net6.0 --language C#
```

## Running the app locally

To run the sample application:

```
dotnet run
```

For more details on Steeltoe components, visit <https://docs.steeltoe.io/>.

## Deploying to Kubernetes as a TAP workload with Tanzu CLI

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.

If you make modifications to the source, push these changes to your own Git repository.

When you are done developing your app, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create sample-app -f config/workload.yaml \
  --local-path . \
  --type web
```

## Accessing the app deployed to your cluster

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get sample-app
```

To access the deployed app use the URL shown under "Workload Knative Services" and append the endpoint `/weatherforecast` to that URL.

This depends on the TAP installation having DNS configured for the Knative ingress.
