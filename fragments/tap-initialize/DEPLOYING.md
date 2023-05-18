# Tanzu Application Platform

## Working on your my-project project

### Deploying to Kubernetes as a TAP workload with Tanzu CLI

If you make modifications to the source you must push the changes to your Git repository.

When you are done developing your app and have pushed the latest changes to your Git repository, then you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like to deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create my-project -f config/workload.yaml \
  --local-path . \
  --type web
```

### Accessing the app deployed to your cluster

Determine the URL to use for accessing the app by running:

```
tanzu apps workload get my-project
```

To access the deployed app use the URL shown under "Workload Knative Services".

This depends on the TAP installation having DNS configured for the Knative ingress.
