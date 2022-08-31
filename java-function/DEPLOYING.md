# Deploying

## Prerequisites

- [curl](https://curl.se/download.html)
- [pack](https://buildpacks.io/docs/tools/pack/) >= `0.23.0`
- [func](https://github.com/knative-sandbox/kn-plugin-func/blob/main/docs/installing_cli.md)

## Building your function

You can build your function using our provided builder, which already includes buildpacks and an invoker layer:

```
pack build my-java-fn --path . --builder ghcr.io/vmware-tanzu/function-buildpacks-for-knative/functions-builder:0.0.12
```

Where `my-java-fn` is the name of your runnable function image, later used by Docker.

## Local Deployment

### Tilt

You may use [tilt](https://github.com/tilt-dev/tilt) `>v0.27.2` in combination with TAP's VS Code plugin to enable live development features including Application Live View and Live Update.

You will have to update some fields in the root directory's `Tiltfile` to connect your live session to Kubernetes.

### Docker

This assumes you have Docker Desktop properly installed and running.

With Docker Desktop running, authenticated, and the ports (default `8080`) available:

```
docker run -it --rm -p 8080:8080 my-java-fn
```

## Handy Tips

### Naming The Function

There is an environment variable BP_FUNCTION.  Use this to point to the Java class that contains your function. 
Note that you can use this variable in the pack cli when building locally and in the config/workload.yaml file when using the tanzu cli (and also `tilt up`)

### Java Dependencies

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

## TAP Deployment - Alpha

### Deploying to Kubernetes

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.


## Deploying to Kubernetes as a TAP workload with Tanzu CLI

You need to select the accelerator option `Include TAP deployment resources` when generating the project for the steps below to function.

When you are done developing your app, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create my-java-fn -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/my-java-fn-source \
  --type web
```

## Interacting with Tanzu Application Platform

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get my-java-fn
```

> NOTE: This depends on the TAP installation having DNS configured for the Knative ingress.

After deploying your function, you can interact with the function by using:

> NOTE: Replace the <URL> placeholder with the actual URL.

### for HTTP

```
curl -w'\n' <URL>/handler \
 -H "Content-Type: application/json" \
 -d '{"firstName":"John", "lastName":"Doe"}' -i
 ```

> Where `/handler` as a path invokes that specific function

### for CloudEvents

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
