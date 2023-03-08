# Deploying

## Prerequisites

In order to further develop this application the following tools may be needed:
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for mentioned IDE
- Docker Desktop to execute integration tests or run the application locally
- [Curl](https://curl.se/download.html) for local testing
- [Pack CLI](https://buildpacks.io/docs/tools/pack/) for local testing

## Local

To quickly test locally, run this command in the directory where `func.py` exists.

```
pack build python-function --path . --builder ghcr.io/vmware-tanzu/function-buildpacks-for-knative/functions-builder:0.3.1 --env BP_FUNCTION=func.main
```

Where `python-function` is the name of your runnable function image.

Then run via Docker:

```
docker run -it --rm -p 8080:8080 python-function
```

### HTTP

Check for a successful response: `curl -X POST http://localhost:8080`

### CloudEvents

If you are using CloudEvents, save the following snippet as a `cloudevent.json` file:

```
{
    "specversion" : "1.0",
    "type" : "com.github.pull_request.opened",
    "source" : "https://github.com/cloudevents/spec/pull",
    "subject" : "123",
    "id" : "A234-1234-1234",
    "time" : "2018-04-05T17:31:00Z",
    "comexampleextension1" : "value",
    "comexampleothervalue" : 5,
    "datacontenttype" : "text/plain",
    "data" : "helloworld"
}
```

Check for a successful response:

```
curl -i -w'\n' -X POST -H "Content-Type: application/cloudevents+json" -d @cloudevent.json http://localhost:8080
```

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
tanzu apps workload create python-function -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/python-function-source \
  --type web
```

### Interacting with Tanzu Application Platform

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get python-function
```

> NOTE: This depends on the TAP installation having DNS configured for the Knative ingress.

After deploying your function, you can interact with the function by using:

> NOTE: Replace the <URL> placeholder with the actual URL.

#### for HTTP

```
curl -w'\n' -X POST <URL>"
```

#### for CloudEvents

If you'd like to test this function, you may use this CloudEvent saved as `cloudevent.json`:

```
{
    "specversion" : "1.0",
    "type" : "com.github.pull_request.opened",
    "source" : "https://github.com/cloudevents/spec/pull",
    "subject" : "123",
    "id" : "A234-1234-1234",
    "time" : "2018-04-05T17:31:00Z",
    "comexampleextension1" : "value",
    "comexampleothervalue" : 5,
    "datacontenttype" : "text/plain",
    "data" : "helloworld"
}
```

> NOTE: that you should change the contents of the CloudEvent you're testing against as you update the function.

```
curl -i -w'\n' -X POST -H "Content-Type: application/cloudevents+json" -d @cloudevent.json <URL>
```

## Runtime Configuration

This accelerator makes use of the
[Function Buildpack for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) Python buildpack. 
Reference its [documentation](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/buildpacks/python) for
further details.  For example, Knative's `func.yaml` file can be used to configure runtime 
environment variables and deployment scaling options. 
