# Pre Built Deployment Flow

This flow is essentially the back half of the build and deploy flow.  It assumes that the application has already been pre-built
with the build's output artifacts already existing in an output directory and then simply deploys the application from that output directory.
This flow is likely not a common flow in a real development environment, but is useful for quickly deploying the application from the
source code repository.  

This `where-for-dinner` directory contains a `demo-prebuilt` directory that consists of the build outputs from a previously run "tanzu build" process.
Similar to the build and deploy flow, this flow also uses Bitnami services.

The high level flow consists of the following steps:

* Clone repository
* Login to tanzu platform
* Set/use the appropriate context, project, and space
* Deploy to development space


## Clone Repository

Clone the application repository by running following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples/
git checkout wfd-spaces-ga
cd where-for-dinner/
```

## Login and Set Project and Space

If you are not already in context of your development space, login into the Tanzu platform and set the appropriate project and space using the following commands:

```
tanzu login
tanzu project use
tanzu space use
```

## Optional: Configure Domain

> This step is optional. Configuration is preset to use `where-for-dinner` hostname.
> 
> Where For Dinner uses an `HTTPRoute` resource to create an externally resolvable and accessible endpoint on the internet.  The hostname portion of the externally 
addressable address is controlled by the `spec.parentRefs.sectionName` of the `HTTPRoute` resource.  The sectionName field's value is prefixed with `http-` and then 
followed by the desired hostname.  For example, a value of `http-where-for-dinner` would result in a hostname of `where-for-dinner`.
> 
> Modify the `.tanzu/config/k8sGatewayRoutes.yaml` file to with the hostname that you would like your app to be available at and save it.

## Deploy The Workloads

To deploy as a single command (referencing pre-built ContainerApps), run the following:

```
tanzu deploy --from-build demo-prebuilt
```
