# Deploying

## Prerequisites

- [curl](https://curl.se/download.html)
- [pack](https://buildpacks.io/docs/tools/pack/) >= `0.23.0`

## Deployment

### Code iteration without OCI image
Use node directly: 
```
npm install
npm start -- --log-level info
curl localhost:8080
```

### Run the image locally using Docker

Build your image and run it using Docker: 
```
pack build node-function --path . --buildpack paketo-buildpacks/nodejs --builder paketobuildpacks/builder:base
docker run -it --rm -p 8080:8080 node-function
curl localhost:8080
```

### Run the image using the tanzu cli

```
rm -rf node_modules
rm package-lock.json
tanzu apps workload apply -f config/workload.yaml --local-path . --source-image <repository>
tanzu apps workload tail node-function
tanzu apps workload get node-function
```
where `<repository>` is the image repository where your source code is staged before being built

### Run the image using Tilt in a Kubernetes cluster

You may use [tilt](https://github.com/tilt-dev/tilt) `>v0.27.2` in combination with TAP's VS Code plugin to enable live development features including Application Live View and Live Update.

Update the `allow_k8s_contexts` line of the `Tiltfile` to indicate the Kubernetes context to use. 

Update the `Tiltfile` or set the SOURCE_IMAGE environment variable to indicate the registry path where TAP should store your image. 

```
export SOURCE_IMAGE=registry/project/my-function-name
export K8S_TEST_CONTEXT="a-kubernetes-context"
tilt up
tilt down
```


## Testing

With our functions, you should see some HTML or sample text returned indicating a success.

### HTTP

After deploying your function, you can interact with the function by running:

```
curl -w'\n' localhost:8080/hire \
 -H "Content-Type: application/json" \
 -d '{"firstName":"John", "lastName":"Doe"}' -i
 ```

> Where `/hire` as a path invokes that specific function

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

