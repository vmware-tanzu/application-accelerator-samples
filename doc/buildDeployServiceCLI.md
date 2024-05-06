# Build/Deploy Flow

This flow is from the perspective of the developer and assumes that the application's repository has already been initialized with build and deployment
configuration.  Configuration includes not only the `ContainerApp` but additional dependent configuration such as Spring Cloud Gateway routes and mappings, 
and an HTTPRoute, however service instances are created and bound using the services CLI. The intention is to build the application and deploy it into a development space as
well as exercise the services CLI flows.  
The high level flow consists of the following steps:

* Clone repository
* Login to tanzu platform
* Set/use the appropriate context, project, and space
* Configure build plan
* Build workloads
* Deploy to development space
* Create and Bind Services

## Clone Repository

Clone the application repository by running following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-ga
cd where-for-dinner-services-cli
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

Before you can build the application, you need to configure your container registry where images will be moved to.
Configure it using the following command.  Note that `{name}` is actually part of the registry URL that you will provide.  Eg: `reg.perfect300rock.com/tapdev/{name}`

```
tanzu build config --containerapp-registry <some-registry.io/some-project/>/{name}
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

## Create and Bind Services

To create and bind services, run the following commands below.


List service instances in the space.  There will likely be 0 instances.

```
tanzu services list
```

List the available service instance types.

```
tanzu services type list
```

Create a Bitnami MySql and RabbitMQ instance.

```
tanzu services create MySQLInstance/where-for-dinner-mysql
tanzu services create RabbitmqCluster/where-for-dinner-rabbitmq
```

List service instances in the space and verify they have been created.

```
tanzu services list
```

Bind the services to the workloads.

```
tanzu services bind MySQLInstance/where-for-dinner-mysql ContainerApp/where-for-dinner-availability --as db
tanzu services bind MySQLInstance/where-for-dinner-mysql ContainerApp/where-for-dinner-search --as db
tanzu services bind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-availability --as rmq
tanzu services bind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-search --as rmq
tanzu services bind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-search-proc --as rmq
tanzu services bind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-notify --as rmq
```

Display information about the MySQL and RabbitMQ Instances and ensure that they are bound to the applications.

```
tanzu services get MySQLInstance/where-for-dinner-mysql
tanzu services get RabbitmqCluster/where-for-dinner-rabbitmq
```

When the services are no longer needed, you can unbind them and delete them from the space.

```
tanzu services unbind MySQLInstance/where-for-dinner-mysql ContainerApp/where-for-dinner-availability --alias db
tanzu services unbind MySQLInstance/where-for-dinner-mysql ContainerApp/where-for-dinner-search --alias db
tanzu services unbind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-availability --alias rmq
tanzu services unbind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-search --alias rmq
tanzu services unbind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-search-proc --alias rmq
tanzu services unbind RabbitmqCluster/where-for-dinner-rabbitmq ContainerApp/where-for-dinner-notify --alias rmq

tanzu services delete MySQLInstance/where-for-dinner-mysql
tanzu services delete RabbitmqCluster/where-for-dinner-rabbitmq
```

Verify the service instances were deleted.

```
tanzu services list
```
