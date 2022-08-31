# Java Function - Using Function Buildpacks for Knative

This repo contains a simple Java Function that can be built using Function Buildpacks and deployed as a TAP serverless workload.

This function utilizes the buildpacks provided by VMware's open-source [Function Buildpacks for Knative](https://github.com/vmware-tanzu/function-buildpacks-for-knative) project.

## Getting Started

To begin editing your function, refer to the tree diagram below of the file to modify:

```
my-java-fn
└── src/main/java/functions
    └── Handler.java // EDIT THIS FILE
    └── models/
```

Inside this file, you will find a main class and function that is invoked by default. Models contains scaffold classes you may edit or delete to build out your Java function. You can change the name of the default class and file, too.

If using TAP workloads, and if you change the name of the default package or class, you will need to update `workload.yaml` to use the correct Java package:

```
spec:
  build:
    env:
    - name: BP_FUNCTION
      value: functions.Handler # UPDATE ME
``` 

For more information about how the environment variable works, please see the [Java buildpack documentation](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/buildpacks/java).

To see samples of code deployable as a Function (FaaS) experience, visit the [samples folder](https://github.com/vmware-tanzu/function-buildpacks-for-knative/tree/main/samples/java).

### Implementation Details (FAQ)

To add/remove dependencies, you may use Maven or Gradle for dependency management as with any normal Java / Spring development.

Instead of arguments in the function definition, the `in` object has attributes that can be populated and accessed.

## Deploying

Please see [DEPLOYING.md](DEPLOYING.md) on how to build, deploy, and test your newly built function.
