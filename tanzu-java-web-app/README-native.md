
## Deploying the sample on TAP built as a GraalVM native image

You can build and deploy the `config/workload-native.yaml` file using the Apply Workload option for Tanzu Developer Tools in your IDE.

You can also use the Tanzu CLI to build and deploy this sample as a GraalVM native image.
With the Tanzu CLI, you can use the following command:

```sh
tanzu apps workload create tanzu-java-web-app \
  --file ./config/workload-native.yaml \
  --local-path .
```
