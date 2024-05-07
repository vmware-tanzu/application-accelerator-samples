# GitOps Flow

This flow is from the perspective of an application operator of similar role that is responsible for deploying the application
into downstream `Spaces` (e.g. staging/production/...).  The `where-for-dinner-gitops` directory is similar to a the structure a GitOps repository that contains
all configuration necessary to deploy the application into downstream `Space`.  The following is a breakdown of the folder structure.

```
.
|- tanzu.yaml <---- Root tanzu file that contains the UUID of the `project` that the spaces are in
|- spaces/  <---- Directory that contains a set of directories of all spaces where the application can be deployed to
    |
    |- where-for-dinner-integration/  <---- Directory that contains deployment configuration for the `where-for-dinner-integration` space
    |  |- bitnamiServices.yaml        <---- Set of files that contain configuration for the integration space
    |  |- k8sGatewayRoutes.yaml
    |  |- scgRoutes.yaml
    |  |- where-for-dinner-availability/ <---- ContainerApp and other configuration resource for the `where-for-dinner-availability` workload
    |  |  |- containerapp.yaml
    |  |  |- package.yaml
    |  |  |- packaginstall.yaml
    |  |  |- package-values.yaml
    |  |
    |  |- where-for-dinner...  <---- The remaining workload folders similar to `where-for-dinner-availability`
    |
    |- where-for-dinner-prod/  <---- Directory that contains deployment configuration for the `where-for-dinner-prod` space  
    |  |- aws.yaml             <---- Set of files that contain configuration for the prod space
    |  |- k8sGatewayRoutes.yaml
    |  |- scgRoutes.yaml
    |  |- where-for-dinner-availability/ <---- PackageInstall, ContainerApp, Package , and values file for the `where-for-dinner-availability` workload
    |  | ...
```

The `tanzu.yaml` file contains the UUID of the project where the application will be deployed.  Each sub directory under the `spaces` directory denotes the name
of the `Space` where the application will be deployed; the directory name and space name MUST match.  To deploy it additional spaces, you can simply copy one 
of the folders above and change configuration as needed.  Future iterations may contain a `promotion` flow to move configuration from one space to another.

The high level flow consists of the following steps:

* Clone repository
* Login to tanzu platform
* Set/use the appropriate project and space
* Deploy to spaces

## Clone Repository

Clone the application repository by running following commands:

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-ga
cd where-for-dinner-gitops
```

## Login and Set Project

If you are not already logged-in into the tanzu platform and set the appropriate project
using the following commands:

```
tanzu login
tanzu project use
```

## Deploy The Workloads

This repository contains space folders named `where-for-dinner-integration` and `where-for-dinner-prod` which infers that you have spaces with these names
already provisioned.  If you wish to deploy to a different space name, you can either rename the directory and copy and paste it to a folder with a different
name.

The `where-for-dinner-integration` and `where-for-dinner-prod` spaces are effectively the same configuration with the exception of the data services they use.  The `where-for-dinner-integration` space uses
Bitnami services whilst the `where-for-dinner-prod` space uses off platform services such as AWS services.  The following sections include instructions needed for deploying
either Bitnami based services or external services.

### Set the Project UUID

You will first need to set the UUID of your project in the `tanzu.yaml` file in the root of the `where-for-dinner-gitops` directory.  You can obtain the UUID by running
the following command:

```
tanzu project list --wide
```

### Deploy To Integration Space

The `where-for-dinner-integration` space configuration uses Bitnami services and is trivial to deploy.  Deploy the application by running the following commands:

```
cd spaces/where-for-dinner-integration
tanzu deploy
```

### Deploy To Prod Space

The `where-for-dinner-prod` space configuration uses external services and requires the `serviceSecrets.example` file to be updated with credentials of external
services such as AWS services.  The following steps will walk you through standing up RDS and RabbitMQ instances on AWS.

#### Create MySQL RDS Instance

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
`Connectivity & security` section.  Note the endpoint as this will be used as the host field in the secret located in the file `serviceSecrets.example`.

#### Create RabbitMQ Cluster

To create a RabbitMQ instance:

- Search for AmazonMQ in the AWS Web Console and click `Create brokers` 
- Select `RabbitMQ` as the broker engine.  
- You can choose settings of your preference through the rest of the wizard, but you will need to provide a username/password which you will need to remember when creating the secret.   
- You should also select `Private access` for `Access type`.  
- After providing all settings, click `Next` then `Create broker`; it may take up to 20 minutes for the broker to be provisioned.

After the broker has been created, click the broker name from the list of brokers and scroll down to the `Connections` sections.  Note the AMQP endpoint 
as this will be used in the addresses field in the secret located in the file `serviceSecrets.example`.

#### Update Service Credential Secrets

Using your editor of choice, update the fields with <> placeholders in the `serviceSecrets.example` file with the credentials 
and connection information for the RabbitMQ and MySQL instances.  You will need to base64 encode each secret/credential value before adding it to the `serviceSecrets.example `
file; an easy way to base64 values is to use an online tool such as https://www.base64encode.org.

#### Deploy Workloads

Deploy the application by running the following commands:

```
cd spaces/where-for-dinner-prod

# One off deploy of your secrets (they would only be kept on cluster -- not in the Git repository)
tanzu deploy --only serviceSecrets.example

# Deploy all other configuration
tanzu deploy
```
