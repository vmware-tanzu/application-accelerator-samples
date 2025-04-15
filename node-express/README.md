# node-express

This is a starter ExpressJs project. 
The server will be listening to request on port `3000`,
so you can test the server in a browser accessing `http://localhost:3000` or via `cURL`.

## Running the application locally

### Building locally
Before running the app, you need to install the dependencies by running this command:

```shell
npm install
```

### Running the application locally

Once the dependencies are installed, you can run it as a standalone app by running the following command from the root of the project:

```shell
npm run server
```

### Access the local application

Access the local application by opening your browser using the URL [http://localhost:3000](http://localhost:3000) or use `cURL`.

## Tanzu Platform deployment

### Prerequisites

You need to be logged in to Tanzu Platform for Cloud Foundry and have set the target org and space.

### Push the app

To push the app to your space, run this command:

```sh
cf push
```

This will deploy the app based on the settings in the `manifest.yml` file.

### Access the app

Find the route assigned to the app using this command:

```sh
cf app node-express
```

The route assigned will be listed under `routes:`.

### Delete the app

To delete the app and remove the assigned route, run the following command:

```sh
cf delete node-express -r
```
