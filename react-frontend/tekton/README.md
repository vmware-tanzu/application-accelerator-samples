# Tekton Pipeline

Before deploying your application a Tekton Pipeline responsible for the testing step should be created in your application
namespace. To support [Pact](https://docs.pact.io/)-based consumer-driven contract testing Python and GCC should be available on an environment where
tests are executed. You need to create a Docker image which provides this kind of environment.
You can use a Docker file to create one - a sample is available here: [Dockerfile](./Dockerfile).

Once you have a `Docker` file you can run the commands shown below.

> Be aware that you have to login to the image registry of your choice beforehand and you must have a write access to this registry.

> **Note:** The image that is created must be an `amd64` image so you can not build this on a system with an ARM processor like the newer Apple systems with M1 or M2 processors.

```bash
 docker build -t <your-image-registry.io>/<your-developer-namespace-project>/react-test-with-pact:node-19 - < Dockerfile
 docker push <your-image-registry.io>/<your-developer-namespace-project>/react-test-with-pact:node-19
```

Please ensure that an image which referenced on line 24 in the [pipeline definition sample](./tekton/react-test-pipeline.yaml)  is the same as the one you created in the previous step.

To create a pipeline you can use following command.

```bash
kubectl apply -f react-test-pipeline.yaml
```

> You can have several pipelines available simultaneously. Matching of a pipeline with a workload is done based on a label assigned to a pipeline. You would need to modify the provided config files accordingly.

> Pipeline:
> ```
> apiVersion: tekton.dev/v1beta1
> kind: Pipeline
> metadata:
>   name: react-test-pipeline
>   labels:
>     apps.tanzu.vmware.com/pipeline: test-react
> ...
> ```

> Workload:
> ```
> apiVersion: carto.run/v1alpha1
> kind: Workload
> ...
> spec:
>   params:
>     - name: testing_pipeline_matching_labels
>       value:
>         apps.tanzu.vmware.com/pipeline: test-react
> ``` 
