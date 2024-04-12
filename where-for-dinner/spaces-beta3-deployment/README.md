# Spaces - Beta 3 Sample App

This directory contains pre-built deliverable packages of the Where For DInner application that can be installed in a run `Space` 
for the Spaces Beta 3 release.  It can consume either AWS RDS and MQ services using direct secret references or Bitnami MySQL and 
RabbitMQ services.  If using AWS services, you will need deploy RDS and Amazon MQ (RabbitMQ option) instances which 
are publicly available in order for the application workloads to properly run.  If using Bitnami services, you can either deploy
and bind services using yaml (using `Bitnami` sub directory) or use the services CLI flow (using `servicescli` sub directory) 


It is also assumed that you have access to a `Space` that has been configured with the proper traits.

## Obtain Where For Dinner Deployment Repository

The configuration files for deploying Where For Dinner to your space can be obtained by cloning the following Git repository and switching to the 
`wfd-spaces-beta3` branch.  Run the following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-beta3
cd where-for-dinner/spaces-beta3-deployment
```

Follow the service creation steps for you desired service deployment option: AWS or Bitnami Services

## Create AWS Services

Use these service creation steps if you have chosen to use AWS service.

This application configuration option of Where For Dinner utilizes AWS MySQL and RabbitMQ managed services and consumes credentials/connection information via a 
direct secret reference.  The following sections walk you through creating an Amazon RDS database and a RabbitMQ cluster that can be consumed 
by the Where For Dinner application.  The subsequent  `Update Service Credential Secrets` section will articulate how you will populate the service 
credentials/connection information into a Kubernetes secret.

### Create MySQL RDS Instance

To create a MySQL instance:

- Search for RDS in the AWS Web Console and click the "Create database" button
- Select `Standard create`, and select "Aurora (MySQL Compatible)
- Select `Dev/Test` as the template 
- Give the cluster a name of your choice and supply a master username and password in `Credential Settings` (you will need the user/password in the appropriate secret fields).  
- For "Instance configuration", select a smaller size in the `Burstable classes` class; db.t4g.large is appropriate.  
- Under `Connectivity`, chose "Yes" for `Public access.`  You can turn off performance insights if you wish.  
- Under `Additional configuration`, provide an "Initial database name such as `dinner` 
- You can disable backups as well if you wish.  
- Click "Create database.

After the database has been created and is available, click on the writer instance of your database from the list of databases clusters and scroll down to the 
`Connectivity & security` section.  Note the endpoint as this will be used as the host field in the secret located in the file *serviceSecret.yaml*.

### Create RabbitMQ Cluster

To create a RabbitMQ instance:

- Search for AmazonMQ in the AWS Web Console and click `Create brokers` 
- Select `RabbitMQ` as the broker engine.  
- You can choose settings of your preference through the rest of the wizard, but you will need to provide a username/password which you will need to remember when creating the secret.   
- You should also select `Private access` for `Access type`.  
- After providing all settings, click `Next` then `Create broker`; it may take up to 20 minutes for the broker to be provisioned.

After the broker has been created, click the broker name from the list of brokers and scroll down to the `Connections` sections.  Note the AMQP endpoint 
as this will be used in the addresses field in  the secret located in the file *serviceSecret.yaml*.


### Update Service Credential Secrets

Using your editor of choice, update the fields with <> placeholders in the `aws/serviceSecret.yaml` file with the credentials 
and connection information for the RabbitMQ and MySQL instances.  You will need to base64 encode each secret/credential value before adding it to the `serviceSecret.yaml `
file; an easy way to base64 values is to use an online tool such as https://www.base64encode.org.

## Bitnami Services


You should only use this option if you are deploying the application into a space that has a single availability target.

This application configuration option of Where For Dinner utilizes the Bitnami MySQL and RabbitMQ on platform services and consumes credentials/connection via 
a Kubernetes Service Binding compliant resource.  You will deploy the service instances in the `Deploy Where For Dinner Application and Configuration To Space` 
section using either the `bitnamiServices.yaml` file or using services CLI commands.


## Update Kubernetes Gateway Route

Where For Dinner uses an `HTTPRoute` resource to create an externally resolvable and accessible endpoint on the internet.  The hostname portion of the externally 
addressable address is controlled by the `spec.parentRefs.sectionName` of the `HTTPRoute` resource.  The sectionName field's value is prefixed with `http-` and then 
followed by the desired hostname.  For example, a value of `http-where-for-dinner` would result in a hostname of `where-for-dinner`.

In the `<service deployment>/routes` directory you will see the “k8GatewayRoutes.yaml” file.  Modify k8sGatewayRoutes.yaml to replace the <hostname> with the hostname 
that you would like your app to be available at and save it.  **NOTE** Make sure you updates the files under the `aws` or `bitnami` folders depending
on your backing service deployment configuration.


## Switch Context To New Space

If you have not already done so, switch your kubernetes context to target your project/space by running the following commands:

```
tanzu project use <project name>
tanzu space use <space name>
```

## Deploy Where For Dinner Application and Configuration To Space

The Where For Dinner deployment consists of the following resources:

- Package and PackageInstall resources
- Secret resources for configuring the PackageInstalls
- If using AWS services, secret resources containing backing service credential/connection info 
- Routing resources for Spring Cloud Gateway and Kubernetes Gateway APIs

To deploy the application, run following command below from the root of the `where-for-dinner/spaces-beta3-deployment` directory depending on the backing 
service configuration:


**AWS Deployment**

```
kubectl apply -f ./aws --recursive
```

**Bitnami Deployment (No Servies CLI)**

```
kubectl apply -f ./bitnami --recursive
```

**Bitnami Deployment (Services CLI)**

This flow deploys the workloads first and then creates and binds services to the workloads using `tanzu services` CLI commands.

```
kubectl apply -f ./bitnami --recursive
```

List service instances in the space.  There will likely be 0 instances.

```
tanzu services instance list
```

List the available service instance types.

```
tanzu services type list
```

Create a Bitnami MySql and RabbitMQ instance.

```
tanzu services instance create MySQLInstance/where-for-dinner-mysql
tanzu services instance create RabbitmqCluster/where-for-dinner-rabbitmq
```

List service instances in the space and verify they have been created.

```
tanzu services instance list
```

Display information about the MySQL and RabbitMQ Instances.

```
tanzu services instance get MySQLInstance/where-for-dinner-mysql
tanzu services instance get RabbitmqCluster/where-for-dinner-rabbitmq
```

Bind the services to the workloads.

```
tanzu services instance bind MySQLInstance/where-for-dinner-mysql Deployment/availability --as db
tanzu services instance bind MySQLInstance/where-for-dinner-mysql Deployment/where-for-dinner-search --as db
tanzu services instance bind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/availability --as rmq
tanzu services instance bind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/where-for-dinner-search --as rmq
tanzu services instance bind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/search-proc --as rmq
tanzu services instance bind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/where-for-dinner-notify --as rmq
```

When the services are no longer needed, you can unbind them and delete them from the space.

```
tanzu services instance unbind MySQLInstance/where-for-dinner-mysql Deployment/availability --alias db
tanzu services instance unbind MySQLInstance/where-for-dinner-mysql Deployment/where-for-dinner-search --alias db
tanzu services instance unbind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/availability --alias rmq
tanzu services instance unbind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/where-for-dinner-search --alias rmq
tanzu services instance unbind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/search-proc --alias rmq
tanzu services instance unbind RabbitmqCluster/where-for-dinner-rabbitmq Deployment/where-for-dinner-notify --alias rmq

tanzu services instance delete MySQLInstance/where-for-dinner-mysql
tanzu services instance delete RabbitmqCluster/where-for-dinner-rabbitmq
```

Verify the service instances were deleted.

```
tanzu services instance list
```

**NOTE:** If using Bitnami services, the workloads may initially crash as the Bitnami services are being deployed.  They should successfully start once the 
MySQL and RabbitMQ services are available.  
