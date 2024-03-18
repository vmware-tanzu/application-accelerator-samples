# Spaces - Beta 2 Sample App

This directory contains pre-built deliverable packages of the Where For DInner application that can be installed in a run `Space` 
for the Beta2 release of Tanzu.  It can consume either AWS RDS and MQ services using direct secret references or Bitnami MySQL and 
RabbitMQ using `ClassClaims`; If using AWS services, you will need deploy an RDS and Amazon MQ (RabbitMQ option) instance which 
is publicly available for the application workloads to properly run.  It is also assumed that you have access to `Space` that has been 
configured with the proper traits.

## Obtain Where For Dinner Deployment Repository

The configuration files for deploying Where For Dinner to your space can be obtained by cloning the following Git repository and switching to the beta2 branch.  
Run the following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-beta2
cd where-for-dinner/spaces-beta2-deployment
```

Follow the service creation steps for you service deploy option: AWS or Bitnami Services

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

After the broker has been created, click the broker name from the list of brokers and scroll down to the `Connections` sections.  Note the AMQP endpoint as this will be used in the addresses field in  the secret located in the file *serviceSecret.yaml*.


### Update Service Credential Secrets

Using your editor of choice, update the fields with <> placeholders in the serviceSecret.yaml file in the root of this repository with the credentials 
and connection information for the RabbitMQ and MySQL instances.  You will need to base64 encode each secret/credential value before adding it to the serviceSecret.yaml 
file; an easy way to base64 values is to use an online tool such as https://www.base64encode.org.

## Create Bitnami Services


Use these service creation steps if you have chosen to use Bitnami service.

This application configuration option of Where For Dinner utilizes the Bitnami MySQL and RabbitMQ on platform services and consumes credentials/connection via a 
`ClassClaim`.  The following commands will create on demand service instances which will be readily consumable by Where For Dinner.


- Switch your kubernetes context to target your project/space by running the following commands:

```
tanzu project use <project name>
tanzu space use <space name>
```

- Deploy the Bitnami services by running the following command from the root of the `where-for-dinner/spaces-beta2-deployment` directory:

```
kubectl apply -f bitnamiServices.yaml --validate=false
```


## Update Kubernetes Gateway Route

Where For Dinner uses an `HTTPRoute` resource to create an externally resolvable and accessible endpoint on the internet.  The hostname portion of the externally 
addressable address is controlled by the `spec.parentRefs.sectionName` of the `HTTPRoute` resource.  The sectionName field's value is prefixed with `http-` and then 
followed by the desired hostname.  For example, a value of `http-where-for-dinner` would result in a hostname of `where-for-dinner`.

In the `routes` directory you will see that you’ve “k8GatewayRoutes.yaml” file.  Modify k8sGatewayRoutes.yaml to replace the <hostname> with the hostname 
that you would like your app to be available at and save it.


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

All of the following commands should be run from the root of the `where-for-dinner/spaces-beta2-deployment` directory.

- If you are using AWS services, install the services secret resources by running the following command:

```
kubectl apply -f serviceSecret.yaml --validate=false
```

- Install the application packages by running the following command:

```
kubectl apply -f ./packages --validate=false
```


Deploy the Where For Dinner application by running the appropriate commands depending on if you are using AWS or Bitnami services configuration:

**AWS Deployment**

```
kubectl apply -f ./package-installs-aws --validate=false
```

**Bitnami Deployment**

```
kubectl apply -f ./package-installs-bitnami --validate=false
```

- Install the routing resources by running the following command:

```
kubectl apply -f ./routes --validate=false
```