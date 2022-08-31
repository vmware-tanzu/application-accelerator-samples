# Python Function - Using Function Buildpacks for Knative

This repo contains a simple Python Function that can be built using Function Buildpacks and deployed as a TAP serverless workload.

This function utilizes the buildpacks provided by VMware's open-source [Function Buildpacks for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) project.

## Getting Started

To begin editing your function, refer to the tree diagram below of the file to modify:

```
my-python-fn
    └── func.py // EDIT THIS FILE
```

Inside this file, you will find a function that is invoked by default. For example:

```
def main(data: Any, attributes: dict):
    # Your function implementation goes here
    return attributes, "Hello world!"
```

You may replace the code inside this default function with your logic.

To see samples of code deployable as a Function (FaaS) experience, visit the [samples folder](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/samples/python).

## Deploying

Please see [DEPLOYING.md](DEPLOYING.md) on how to build, deploy, and test your newly built function.
