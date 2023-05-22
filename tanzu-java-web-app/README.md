# tanzu-java-web-app

This is a sample of a Java Spring app that works with Tilt and the Tanzu Application Platform.

## Dependencies
1. [kubectl CLI](https://kubernetes.io/docs/tasks/tools/)
2. [Tilt version >= v0.23.2](https://docs.tilt.dev/install.html)
3. Tanzu CLI and the apps plugin v0.2.0 which are provided as part of [Tanzu Application Platform](https://network.tanzu.vmware.com/products/tanzu-application-platform)
4. A cluster with Tanzu Application Platform, and the "Default Supply Chain", plus its dependencies. This supply chains is part of [Tanzu Application Platform](https://network.tanzu.vmware.com/products/tanzu-application-platform).

## Application Live View
The workload is set up by default to autoconfigure the actuators. This results in that the Spring Actuators are available at TCP port 8081 and will be used by Application Live View.
Application Live View allows you see all health metrics in the TAP GUI. If you would like to have the Actuators available at TCP port 8080 you can set the
annotation `apps.tanzu.vmware.com/auto-configure-actuators` to `false`.

## Building with local source

You can build using source from either a Git repository or from source on your local disk.
The instructions below use the latter option, where you build using the source from your local disk.
This is specified by adding a `--local-path` option providing the path for the source, and a `--source-image` option providing the OCI repository (e.g. `registry.io/user/tanzu-java-web-app-source`) to use for publishing the local source code.

You can set this as an environment variable before running any of the commands below using: 

```sh
export SOURCE_IMAGE=registry.io/user/tanzu-java-web-app-source
```

## Deploying the sample for TAP

Start the app deployment by running:

```sh
tanzu apps workload create tanzu-java-web-app \
  --file ./config/workload.yaml \
  --local-path . \
  --source-image "${SOURCE_IMAGE}"
```

## Running the sample on TAP using Tilt for live update

You can use the IDE plugins for VSCode or IntelliJ IDEA to enable live update. You can also use the command line following these steps.

1. Set the environment variable mentioned above specifying the repository to use for the source image. This is where the local source code will be written. As an example, use `export SOURCE_IMAGE=registry.io/user/tanzu-java-web-app-source`.
2. Start `Tilt` by running `tilt up`
    > If you see an "Update error" message like the one below, then just follow the instructions and allow that context:
        ```
        Stop! tap-beta2 might be production.
        If you're sure you want to deploy there, add:
            allow_k8s_contexts('tap-beta2')
        to your Tiltfile. Otherwise, switch k8s contexts and restart Tilt.
        ```
3. You can hit the spacebar to open the Tilt UI in a browser. 
