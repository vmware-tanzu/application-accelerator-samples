## Deploying to Tanzu Application Service

Push the app to TAS:

```bash
cf push -f manifest.yaml
```

## Accessing the pushed app

Determine the URL to use for accessing the app by running:

```bash
cf apps
```

To access the deployed app use the URL shown under "routes" and append the endpoint `/weatherforecast` to that URL.
