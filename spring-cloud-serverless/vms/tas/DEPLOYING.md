### Deploying on Tanzu Application Service

Build a JAR file locally using Maven:
```bash
mvn clean package
```

Deploy the JAR on TAS using the `cf` CLI:
```bash
cf push -p ./target/hello-fun-0.0.1-SNAPSHOT.jar -f manifest.yaml`
```

### Accessing the App Pushed to Tanzu Application Service

Determine the URL to use to access the app by running:

```
cf app hello-fun
```

To query the deployed app run the following `curl` command in another terminal window:

```
curl ${URL} -w'\n' -H 'Content-Type: text/plain' -d Fun
```
