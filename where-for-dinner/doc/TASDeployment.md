# Tanzu Application Services (TAS) Deployment

The Where for Dinner TAS deployment options are enabled through a Tanzu application accelerator and will generate TAS manifest files for you.  You will
still be responsible for creating service instances, however the manifest files will include service binding configuration.  

## Prerequisites

* Tanzu Application Service
* Spring Cloud Services for VMware Tanzu
* Spring Cloud Gateway for VMware Tanzu
* VMware RabbitMQ for Tanzu® Application Service
* VMware SQL with MySQL for Tanzu Application Service (required if using the `MySql` database option)
* Redis for VMware Tanzu Application Service (required if using the `Redis` cache option)
* JDK 21+
* npm

## Quick Start

This section provides a fast track installation of the "simplest" configuration of the Where For Dinner application using the application accelerator 
and the instructions immediately below.  A more thorough description of the configuration and installation scenarios are describes in subsequent sections of this page.  
This section assumes you have already installed the application accelerator using the instructions at the top of the page.

* Navigate to your TAP GUI web page and Application Accelerator tab on the left of the screen.  Select the `Choose` button on the `Where for Dinner` Application

* Select all defaults, gernerare, download, and unzip the generate accelerator file to you workstation.

* Open a command shell and navigate to the root directory of the unzipped file from above.  Run the following commands to create the necessary services replacing
<plan> with the appropriate plan name.

```
cf create-service p.rabbitmq <plan> msgbroker-where-for-dinner
cf create-service p.service-registry <plan> registry-where-for-dinner
cf create-service p.gateway <plan> gateway-where-for-dinner -c '{ "host": "where-for-dinner" }'
```


* After all of the services above have been successfully deployed, build and deploy the micro-services to your space by running following commands
from the root of the extracted accelerator zip file:

```
cd where-for-dinner-ui
npm install
npm run build
cf push

cd ../where-for-dinner-search
./mvnw clean package
cf push

cd ../where-for-dinner-search-proc
./mvnw clean package
cf push

cd ../where-for-dinner-crawler
./mvnw clean package
cf push

cd ../where-for-dinner-notify
./mvnw clean package
cf push

cd ../where-for-dinner-availability
./mvnw clean package
cf push
```

* Create routes to direct HTTP traffic through the gateway to the micro-services:

```
cf bind-service where-for-dinner-search gateway-where-for-dinner -c '{ "routes": [ { "path": "/api/search/**", "order": 1, "filters": [ "StripPrefix=0", "RewritePath=/api/search(?<segment>/?.*), $\\{segment}", "RemoveRequestHeader=Forwarded" ] } ]  }'

cf bind-service where-for-dinner-availability gateway-where-for-dinner -c '{ "routes": [ { "path": "/api/availability/**", "order": 1, "filters": [ "StripPrefix=0", "RewritePath=/api/availability(?<segment>/?.*), $\\{segment}", "RemoveRequestHeader=Forwarded" ] } ]  }'

cf bind-service where-for-dinner-ui gateway-where-for-dinner -c '{ "routes": [ { "path": "/**", "order": 1000, "filters": [ "StripPrefix=0" ] } ]  }'
```

**Verify Application Build and Deployment**

Run the following command to verify that applications built and deployed successfully.  

```
cf apps
```

You should see a result similar to the following:

```
Getting apps in org meyerg / space dev as meyerg...

name                            requested state   processes           routes
where-for-dinner-availability   started           web:1/1, task:0/0   where-for-dinner-availability.apps.dhaka.cf-app.com
where-for-dinner-notify         started           web:1/1, task:0/0
where-for-dinner-crawler        started           web:1/1, task:0/0   where-for-dinner-crawler.apps.dhaka.cf-app.com
where-for-dinner-search         started           web:1/1, task:0/0   where-for-dinner-search.apps.dhaka.cf-app.com
where-for-dinner-search-proc    started           web:1/1, task:0/0   
where-for-dinner-ui             started           web:1/1             where-for-dinner-ui.apps.dhaka.cf-app.com
```

## Configuration Option Overview  

The TAS deployment of Where For Dinner supports a subset of the options presented in the accelerator; some options are hard set (like use of a service
registry and spring cloud gateway).  Supported options include:

* H2 (In Memory) vs MySQL vs Postgres database options
* In Memory Cache vs Redis cache option
* Spring Cloud Config

Options not yet support include:

* AppSSO
* Native Images
* Curated APIs
* Alternate Implementations (e.g. Python crawler service)

### MySQL Service Configuration

If you choose to you use MySQL as your database option, you will need to create a MySQL service instance in your Space.  Run the following command to create a MySQL
instance replacing <plan> with the appropriate plan name:

```
cf create-service p.mysql <plan> db-where-for-dinner
```

### Redis Service Configuration

If you choose to you use Redis as your chaching option, you will need to create a Redis service instance in your Space.  Run the following command to create a Redis
instance replacing <plan> with the appropriate plan name:

```
cf create-service p.redis <plan> cahce-where-for-dinner
```

### Config Server Configuration

If you choose to you consume external configuration using Spring Cloud Config, you will need to create a Config Server instance in your Space.  Run the following command 
to create a Config instance replacing <plan> with the appropriate plan name, <repo> with the URL of your Git repository, and optionally the <username> and <password>
if required.  Additional configuration options can be found 
[here](https://docs.vmware.com/en/Spring-Cloud-Services-for-VMware-Tanzu/3.2/spring-cloud-services/GUID-config-server-configuring-with-git.html).

```
cf create-service p.config-server <plan> config-where-for-dinner -c '{"git": { "uri": "<repo>",  "username": "<username>", "password": "<password>"} }'
```

## Testing the Deployment

Assuming the application has successfully deployed, you can test the application navigating to the application's URL with a web browser.  The URL should be:

```
https://where-for-dinner.<domain>
```

where <domain> will be the primary domain assigned to your Space.