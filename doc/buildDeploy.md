# Build/Deploy Flow

This flow is from the perspective of the developer and assumes that the application's repository has already been initialized with build and deployment
configuration.  Configuration includes not only the `ContainerApp` but additional dependent configuration such as Spring Cloud Gateway routes and mappings, 
services instances and bindings, and an HTTPRoute. The intention is to build the application and deploy it into a development space.  
The high level flow consists of the following steps:

* Clone repository
* Login to tanzu platform
* Set/use the appropriate context, project, and space
* Configure build plan
* Build workloads
* Deploy to development space

Development environments typically do not need production ready data services like Amazon RDS or Amazon MQ.  Instead they can use simple to deploy
services such as those provided in the Tanzu Bitnami Services package.  The build/deploy flow uses Bitnami services.

## Clone Repository

Clone the application repository by running following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-ga
cd where-for-dinner
```

## Login and Set Context/Project/Space

If you are not already in context of your development space, login into the tanzu platform and set the appropriate context, project, and space
using the following commands:

```
tanzu login
tznzu context use <context name>
tanzu project use <project name>
tanzu space use <space name>
```

## Configure Build Plan

Before you can build the application, you need to configure your build plan as well as the container registry where images will be moved to.
Your space may already have a build plan configured in which case you just use that build plan (using the UCP option).  If you space does not
contain a build plan, one has been provided for you in this repository.

To use the the UCP provided build plan, configure it using the following command.  Note that you will need to provide your registry location.  Also note that
`{name}` is actually part of the registry URL that you will provide.  Eg: `reg.perfect300rock.com/tapdev/{name}`

```
tanzu build config --build-plan-source-type=ucp --containerapp-registry <some-registry.io/some-project/>/{name}
```

If you Space does not have a build plan defined, you can the build plan provided in this repository by running the following command:

```
tanzu build config --build-plan-source-type=file --build-plan-source=platform-config.yaml --containerapp-registry <some-registry.io/some-project/>/{name}
```

If you are not already logged into you container registry, login using the following command (assuming you are using docker):

```
docker login <some-registry.io>
```

## Update the HTTPRoute

Where For Dinner uses an `HTTPRoute` resource to create an externally resolvable and accessible endpoint on the internet.  The hostname portion of the externally 
addressable address is controlled by the `spec.parentRefs.sectionName` of the `HTTPRoute` resource.  The sectionName field's value is prefixed with `http-` and then 
followed by the desired hostname.  For example, a value of `http-where-for-dinner` would result in a hostname of `where-for-dinner`.

Modify the `.tanzu/config/k8sGatewayRoutes.yaml` file to replace the `<hostname>` placeholder with the hostname that you would like your app to be available at and save it.  


## Build and Deploy Workloads

There are a couple of varations to the build and deploy flow.  You can either break the build and deploy into two parts, or combine them into a single command.


### Build and Deploy as One Command

To build and then deploy as a single command, run the following:

```
tanzu deploy
```

### Build and Deploy as Separate Commands

When breaking the build and deploy into separate commands, it may be easier to specify the location of the build's out put artifacts.  Run the following commands to
build the workloads, output the artifacts to a directly named `build`, and then deploy the workloads from the `build` output directory:

```
tanzu build --output-dir build
tanzu deploy --from-build build
```