# python-flask

This is a starter Python Flask project. 
The server will be listening to request on port `8000`,
so you can test the server in a browser accessing `http://localhost:8000` or via `cURL`.

## Running the application locally

### Setting up local virtual environment

Before running the app, you can create a virtual environment by running these commands:

```shell
python3 -m venv venv
. venv/bin/activate
pip install Flask
```

### Running the application locally

Once the virtual environment is activated, you can run it as a standalone app by running the following command from the root of the project:

```shell
FLASK_APP=web.py flask run --port 8000
```

### Access the local application

Access the local application by opening your browser using the URL [http://localhost:8000](http://localhost:8000) or use `cURL`.

<!-- #IF(#deploymentType == 'tanzu') -->
## Tanzu Platform deployment

### Prerequisites

1. Access to [Tanzu Platform](https://docs.vmware.com/en/VMware-Tanzu-Platform/index.html) configured for platform builds.
1. The latest Tanzu CLI and plugins from `vmware-tanzu/app-developer` group installed. Installation instructions can be found in the Tanzu Platform documentation: [Before you begin](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/getting-started-deploy-app-to-space.html#before-you-begin-0).
1. You have access to a space for your project and you have used `tanzu login` to authenticate and configure your current Tanzu context and set your project and space using `tanzu project use` and `tanzu space use` respectively.

### About the ContainerApp

Change to the root directory of your generated app.

The project contains a `ContainerApp` manifest file that can be used when building and deploying the app. To review the content of this file run:

```sh
cat .tanzu/config/python-flask.yml
```

### Configure HTTP Ingress Routing

If you want to expose your application with a domain name and route traffic from the domain name to the deployed application, see [Adding HTTP Routing to an Application](https://docs.vmware.com/en/VMware-Tanzu-Platform/SaaS/create-manage-apps-tanzu-platform-k8s/how-to-ingress-to-app.html).

### Build and deploy the app

Change to the root directory of your generated app.

You can build and deploy the app with a single command.
Just run:

```sh
tanzu deploy
```

### Check the status of the app deployment

You can run this command to see the status of the app deployment:

```shell
tanzu apps get python-flask
```

### Use port-forward to access an app instance

You can use the `app port-forward` command to access your app instance's endpoint.
Just select the instance you want when prompted.
Use the following command to start the port-forward:

```shell
tanzu app port-forward python-flask --port 8000
```

Then you can access the app using http://localhost:8000.
<!-- #ENDIF -->
