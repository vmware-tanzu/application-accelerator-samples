# react-frontend

react-frontend provides you an out-of-the-box application setup to fast start development of a Web Application based
on a Single Page Architecture: the UI is rendered in the browser, the data is retrieved and changed by (RESTful) API calls.

It is leveraging ReactJS as a technology stack, which provides:
- a way to implement UI elements using Components
- a way to re-use Components with Properties and State
- an integrated Web Server for local development, so no need to (re)build the artifact to a separate running web server

## Software Architecture
This simple application contains two pages: one showing a list of customer profiles and another one which can be used
to create a new customer profile. The data is supplied by a backend through a REST API.

The application showcases several ReactJS best practices:
1. Separation between JSON structure (which is used to transfer data over wire) and a typesafe domain object to work with within the app. 
This can be seen in `CustomerProfileService.ts` which is responsible for retrieving and posting `CustomerProfile` data: therefor it maps the DTO to the Domain Object.

2. Separation between representational components and components responsible for a communication with backend. The former ones
are responsible for rendering UI and providing a proper layout. They can be easily tested without any mocks needed.
The components responsible for communication with a backend can be tested by mocking the service modules.
    - `Navigation`, `PageHeader` and `ErrorMessage` are examples of re-usable components as they do not have any domain knowledge
    - `CreateCustomerProfileForm` and `CustomerProfilesTable` are example of re-usable components for domain objects, but are only responsible for representation. Any data needed is given by properties, 
   and any handling needed upon user action are delegated to actions provided by properties.
    - `CreateCustomerProfilePage` and `CustomerProfiles` are components responsible for controlling actions with services.

3. Usage of consumer-driven tests to test communication with API.
    - The `CustomerProfileService` is tested by leveraging Pact: an integration test that bootstraps a local webservice that responds with predefined responses. This 
   way the backend service is tested fully without setting up a whole test environment.

## Prerequisites
In order to further develop this application the following tools needs to be setup:
- NodeJS LTS (https://nodejs.org/) with NPM
- Visual Studio Code or JetBrains IntelliJ/WebStorm as Integrated Development Environment (IDE)
- Tanzu Developer Tools plugin for the mentioned IDE

> This application contains only UI and expect several backend services to be available. You can either generate a backend based
> on the *Tanzu Java Restful Web App* Accelerator. Or build your own which shall provide 2 endpoints:
> - GET /api/customer-profiles/ returning an array of customer profiles in the form of  
    > ```{"id":"<unique-id>", "firstName":"<first name>", "lastName":"<last name>", "email":"<email>" }```
> - POST /api/customer-profiles/ accepting a customer profile in the form of  
    > ```{"firstName":"<first name>", "lastName":"<last name>", "email":"<email>" }```

# Local

## Run NPM install

```bash
$ npm install
```

## Test
It is a good habit to test and execute those tests to see if your application is still behaving as you would expect:

```bash
$ npm test
```

## Start and interact
Before being able to launch the application one shall configure where the backend services can be found. To do it please update the *proxy*
property in the `package.json` file.

React has its own integrated Development Web Server. Launch the application by:
```bash
$ npm start-dev
```

### Accessing home page
You can access the public page at `http://localhost:3000/` by a web browser.

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
    az spring app create -n react-app
```

* Define a builder for Tanzu Build Service

```shell
   az spring build-service builder create -n full-builder --builder-file azure/builder.json 
```

* Build and Deploy the Application

```shell
    az spring app deploy -n react-app \
        --source-path . --builder=full-builder \
        --build-env BP_WEB_SERVER=nginx BP_NODE_RUN_SCRIPTS=build BP_WEB_SERVER_ROOT=build BP_WEB_SERVER_ENABLE_PUSH_STATE=true
```

* Define Gateway Routes

```shell
    az spring gateway route-config create --name frontend --app-name react-app --routes-file azure/routes.json
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
A running pod of this workload will include a built React application, NGINX server and NGINX configuration. A built React application
contains set of JavaScript, HTML, CSS and other static files which will be served by the NGINX HTTP server. Additionally to it the NGINX server
will act as a reverse proxy rerouting requests to the backend services. The rerouting rules you can find and adapt in the NGINX configuration
located in *nginx.conf* (see lines 161-163).  
![Deployment Topology](DeploymentTopology.png)


# How to proceed from here?
Having the application locally running and deployed to a cluster you could add additional Components and Services to interact
with your API.

# Resources
- [Azure Spring Apps](https://learn.microsoft.com/en-us/azure/spring-apps/overview#enterprise-tier-overview)
- [Tanzu Web Servers Buildpack](https://docs.vmware.com/en/VMware-Tanzu-Buildpacks/services/tanzu-buildpacks/GUID-web-servers-web-servers-buildpack.html)
- [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started)
- [React documentation](https://reactjs.org/)
- [Pact Foundation](https://github.com/pact-foundation) - Consumer Driven Test Framework
