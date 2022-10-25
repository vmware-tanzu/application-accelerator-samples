# Deploying

## Prerequisites

In order to further develop this application the following tools may be needed:
- Visual Studio Code or IntelliJ IDEA as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for mentioned IDE
- Docker Desktop to execute integration tests or run the application locally
- [Curl](https://curl.se/download.html) for local testing
- [Pack CLI](https://buildpacks.io/docs/tools/pack/) for local testing

## Local

To quickly test locally, run this command in the root directory.

```
pack build java-function --path . --builder ghcr.io/vmware-tanzu/function-buildpacks-for-knative/functions-builder:0.1.0 --env BP_FUNCTION=functions.Handler
```

Where `java-function` is the name of your runnable function image, later used by Docker.

Then run via Docker:

```
docker run -it --rm -p 8080:8080 java-function
```

Check for a successful response with the commands in the Testing section below.

## Testing

With our functions, you should see some HTML or sample text returned indicating a success.

### HTTP

After deploying your function, you can interact with the function by running:

```
curl -w'\n' localhost:8080/handler \
 -H "Content-Type: application/json" \
 -d '{"firstName":"John", "lastName":"Doe"}' -i
 ```

> Where `/handler` as a path invokes that specific function

### CloudEvents

If you'd like to test this function, you may use this CloudEvent saved as `cloudevent.json`:

```
{
    "specversion" : "1.0",
    "type" : "hire",
    "source" : "https://spring.io/",
    "id" : "A234-1234-1234",
    "datacontenttype" : "application/json",
    "data": {
        "firstName": "John",
        "lastName": "Doe"
    }
}
```

> NOTE: that you should change the contents of the CloudEvent you're testing against as you update the function.

After [deploying](https://github.com/vmware-tanzu/function-buildpacks-for-knative/blob/main/DEPLOYING.md) your function as an image, you can test with:

```
curl -X POST -H "Content-Type: application/cloudevents+json" -d @cloudevent.json http://localhost:8080
```

## Tanzu Application Platform (TAP)

Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.


### Deploying to Kubernetes as a TAP workload with Tanzu CLI

You need to select the accelerator option `Include TAP deployment resources` when generating the project for the steps below to function.

When you are done developing your app, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create java-function -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/java-function-source \
  --type web
```

### Interacting with Tanzu Application Platform

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get java-function
```

> NOTE: This depends on the TAP installation having DNS configured for the Knative ingress.

After deploying your function, you can interact with the function by using:

> NOTE: Replace the <URL> placeholder with the actual URL.

#### for HTTP

```
curl -w'\n' <URL>/handler \
 -H "Content-Type: application/json" \
 -d '{"firstName":"John", "lastName":"Doe"}' -i
 ```

> Where `/handler` as a path invokes that specific function

#### for CloudEvents

If you'd like to test this function, you may use this CloudEvent saved as `cloudevent.json`:

```
{
    "specversion" : "1.0",
    "type" : "hire",
    "source" : "https://spring.io/",
    "id" : "A234-1234-1234",
    "datacontenttype" : "application/json",
    "data": {
        "firstName": "John",
        "lastName": "Doe"
    }
}
```

> NOTE: that you should change the contents of the CloudEvent you're testing against as you update the function.

```
curl -X POST -H "Content-Type: application/cloudevents+json" -d @cloudevent.json <URL>
```

### Using Tilt with a Cluster

You may use [tilt](https://github.com/tilt-dev/tilt) `>v0.27.2` in combination with TAP's VS Code plugin to enable live development features including Application Live View and Live Update.

You will have to update some fields in the root directory's `Tiltfile` to connect your live session to Kubernetes.

Update the `allow_k8s_contexts` line of the `Tiltfile` to indicate the Kubernetes context to use. 

Update the `Tiltfile` or set the SOURCE_IMAGE environment variable to indicate the registry path where TAP should store your image. 

```
export SOURCE_IMAGE=registry/project/java-function
export K8S_TEST_CONTEXT="a-kubernetes-context"
tilt up
tilt down
```

## Java Dependencies

If you need to add dependencies to your Java function, use Maven or Gradle in the normal fashion.  The Maven and Gradle build files default to building a fat runnable jar.  This allows your additional dependencies to be included and available during runtime. 

For example, you could add the following to pom.xml:

```
<dependencies>
  <dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.7</version>
  </dependency>
</dependencies>
```

which would allow you to add `import org.apache.commons.lang3.StringUtils;` to your Java code and make use of StringUtils.

## Runtime Configuration

This accelerator makes use of the
[Function Buildpack for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) Java buildpack.
Reference its [documentation](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/buildpacks/java) for
further details.  For example, Knative's `func.yaml` file can be used to configure runtime environment variables and deployment scaling options. 
