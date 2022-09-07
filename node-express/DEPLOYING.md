# Deploying to Kubernetes

> NOTE: The provided `config/workload.yaml` file uses the Git URL for this sample. When you want to modify the source, you must push the code to your own Git repository and then update the `spec.source.git` information in the `config/workload.yaml` file.

## Deploying to Kubernetes as a TAP workload with Tanzu CLI

When you are done developing your app, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create node-express -f config/workload.yaml \
  --local-path . \
  --source-image <REPOSITORY-PREFIX>/node-express-source \
  --type web
```

## Accessing the app deployed to your cluster

If you don't have `curl` installed it can be installed using downloads here: https://curl.se/download.html

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get node-express
```

To access the deployed app open the URL shown in your browser.

This depends on the TAP installation having DNS configured for the Knative ingress.
