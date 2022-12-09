# angular-frontend

angular-frontend provides you an out-of-the-box application setup to fast-start development of a Web Application based
on a Single Page Architecture: the UI is rendered in the browser, the data is retrieved and changed by (RESTful) API calls. Additionally, this aims to model an architecture where the frontend is deeply decoupled from the backend and have individual teams managing their respective components.

It is leveraging Angular as a technology stack, which provides:
- a way to implement UI elements using Components and Binding
- an Inversion of Control Container to wire together your Components and Services
- an integrated Web Server, so no need to deploy the built artifact to a separate running web or application server

The application contains example code to have a first page that lists customer profiles from an API.

It provides ways to test the service and component logic.

## Prerequisites
In order to further develop this application the following tools needs to be set up:
- NodeJS LTS (https://nodejs.org/) with NPM
- Angular CLI (https://angular.io/cli) (will be part of the development dependencies of the application)
- Visual Studio Code or JetBrains IntelliJ/WebStorm as Integrated Development Environment (IDE)
* [Azure CLI version 2.42.0 or higher](https://docs.microsoft.com/cli/azure/install-azure-cli?view=azure-cli-latest)
* [Azure CLI spring extension](https://learn.microsoft.com/en-us/cli/azure/spring)

> This application contains only UI and expect several backend services to be available. You can either generate a backend based
> on the *Java Spring Restful Web App* Accelerator. Or build your own which shall provide 2 endpoints:
> - GET /api/customer-profiles/ returning an array of customer profiles in the form of  
> ```{"id":"<unique-id>", "firstName":"<first name>", "lastName":"<last name>", "email":"<email>" }```
> - POST /api/customer-profiles/ accepting a customer profile in the form of  
> ```{"firstName":"<first name>", "lastName":"<last name>", "email":"<email>" }```

# Local
Make sure to install dependencies first by executing:
```bash
$ npm install
```

## Test
It is a good habit to test and execute those tests to see if your application is still behaving as you would expect:

```bash
$ ng test
```

## Start and interact
Before being able to launch the application one shall configure where the backend services can be found. To do it please update the *target*
property in the `src/proxy.conf.json` file.  
Angular has its own integrated Development Web Server. Launch the application by:
```bash
$ ng serve
```

### Accessing home page
You can access the public page at `http://localhost:4200/` by a web browser.

# Deployment
## Azure Spring Apps
Using the Azure CLI it is possible to build and deploy this application onto an Azure Spring Apps instance.

To build and deploy this application to Azure Spring Apps, follow these steps.

* Configure Azure CLI defaults to shorten commands

```shell
    az configure --scope local --defaults group=$RESOURCE_GROUP location=$LOCATION spring=$ASA_INSTANCE_NAME
```

* Create an Application
 
```shell
    az spring app create -n angular-app
```

* Build and Deploy the Application

```shell
    az spring app deploy -n angular-app \
        --source-path . \
        --build-env BP_WEB_SERVER=nginx BP_NODE_RUN_SCRIPTS=build BP_WEB_SERVER_ROOT=dist BP_WEB_SERVER_ENABLE_PUSH_STATE=true
```

* Define Gateway Routes

```shell
    az spring gateway route-config create --name frontend --app-name angular-app --routes-file routes.json
```

* Configure Spring Cloud Gateway

```shell
    az spring gateway update --assign-endpoint true
    export GATEWAY_URL=$(az spring gateway show | jq -r '.properties.url')
        
    az spring gateway update \
        --server-url "https://${GATEWAY_URL}" \
        --allowed-origins "*" \
        --allowed-methods "*" \
        --allowed-headers "*" 
```

## Testing the Deployment

* Access Application Using Spring Cloud Gateway

Assuming the application has successfully deployed, you can test the application navigating to the application's URL with a web browser.  To can get the URL with the following command:

```shell
    az spring gateway show | jq -r '.properties.url'
```

## Deployment topology
A running pod of this workload will include a built Angular application, NGINX server and NGINX configuration. A build Angular application 
contains set of JavaScript, HTML, CSS and other static files which will be served by the NGINX HTTP server. Additionally to it the NGINX server
will act as a reverse proxy rerouting requests to the backend services. The rerouting rules you can find and adapt in the NGINX configuration
located in *nginx.conf* (see lines 161-163).  
![Deployment Topology](DeploymentTopology.png)  
 

# How to proceed from here?
Having the application locally running and deployed to a cluster you could add additional Components, Templates and Services to interact
with your API.


