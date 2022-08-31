# Deploying

## Prerequisites
- [curl](https://curl.se/download.html)
- [pack](https://buildpacks.io/docs/tools/pack/) >= `0.23.0`
- [func](https://github.com/knative-sandbox/kn-plugin-func/blob/main/docs/installing_cli.md)

## Building your function

You can build your function using our provided builder, which already includes buildpacks and an invoker layer.

To do so, you can run this command from the `src/` directory, where `func.py` exists.

```
pack build my-python-fn --path . --builder ghcr.io/vmware-tanzu/function-buildpacks-for-knative/functions-builder:0.0.12
```

Where `my-python-fn` is the name of your runnable function image, later used by Docker.

## Local Deployment

### Docker

This assumes you have Docker Desktop properly installed and running.

With Docker Desktop running, authenticated, and the ports (default `8080`) available:

```
docker run -it --rm -p 8080:8080 my-python-fn
```

## Testing

With our function, you should see some HTML or sample text returned indicating a success.

### HTTP

After deploying your function, you can interact with our function by running:

```
curl -X POST http://localhost:8080
```

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

Then you can run:

```
curl -i -w'\n' -X POST -H "Content-Type: application/cloudevents+json" -d @cloudevent.json http://localhost:8080
```

## TAP Deployment - Alpha

### Deploying to Kubernetes

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.


## Deploying to Kubernetes as a TAP workload with Tanzu CLI

You need to select `Include TAP deployment resources` when generating the project for the steps below to work.

When you are done developing your function, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create my-python-fn -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/my-python-fn-source \
  --type web
```

## Interacting with Tanzu Application Platform

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get my-python-fn
```

> NOTE: This depends on the TAP installation having DNS configured for the Knative ingress.

After deploying your function, you can interact with the function by using:

> NOTE: Replace the <URL> placeholder with the actual URL.

### for HTTP

```
curl -w'\n' -X POST <URL>"
```

### for CloudEvents

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
