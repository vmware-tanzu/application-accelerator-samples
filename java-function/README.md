# Java Function

This repo contains a simple Java Function that can be deployed as a TAP workload.

This function utilizes the buildpacks provided by VMware's open-source [Function Buildpacks for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) project.

## Getting Started

To begin editing your function, refer to the tree diagram below of the file to modify:

```
java-function
└── src/main/java/functions
    └── Handler.java // EDIT THIS FILE
    └── models/
```

Inside this file, you will find a example main class, scaffolding, and a function that is invoked by default. 

If using TAP workloads, and if you change the name of the default package or class, you will need to update `workload.yaml` to use the correct Java package:

```
spec:
  build:
    env:
    - name: BP_FUNCTION
      value: functions.Handler # UPDATE TO YOUR PACKAGE NAME
``` 

For more information about how the environment variable works, please see the [Java buildpack documentation](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/buildpacks/java).

To see samples of code deployable as a Function (FaaS) experience, visit the [samples folder](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/samples/java).

## Deploying

Please see [DEPLOYING.md](DEPLOYING.md) on how to build, deploy, and test your newly built function.
