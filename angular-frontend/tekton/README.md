# Tekton Pipeline

Before deploying your Angular app, a Tekton Pipeline responsible for the testing step should be available in your application namespace. 

If you don't already have one, then you could deploy the sample pipeline by running:

```bash
kubectl apply -f https://raw.githubusercontent.com/vmware-tanzu/application-accelerator-samples/main/angular-frontend/tekton/test-pipeline.yaml
```

## Using multiple pipelines 

You can have several pipelines available simultaneously. Matching of a pipeline with a workload is done based on a label assigned to a pipeline. You would need to modify the provided config files accordingly.

> Pipeline:

> ```yaml
> apiVersion: tekton.dev/v1beta1
> kind: Pipeline
> metadata:
>   name: angular-test-pipeline
>   labels:
>     apps.tanzu.vmware.com/pipeline: test-angular
> ...
> ```  

> Workload:

> ```yaml
> apiVersion: carto.run/v1alpha1
> kind: Workload
> ...
> spec:
>   params:
>     - name: testing_pipeline_matching_labels
>       value:
>         apps.tanzu.vmware.com/pipeline: test-angular
> ```
