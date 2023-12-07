# Deploying to Tanzu Application Service

Deploy the app on TAS using the cf CLI:

```bash
cf push -f manifest.yaml
```

## Accessing the App Pushed to Tanzu Application Service

Determine the URL to use to access the app by running:

```
cf app node-express
```

To access the deployed app, open the URL shown in your browser.
