# Configuring a database

## Local with PostgreSQL

Running a PostgreSQL instance can easily be done by using `docker compose`:

```bash
docker compose up -d
```

## Using PostgreSQL as the database with Tanzu Application Platform (TAP)

Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

As with the local deployment a PostgreSQL instance needs to be available at the cluster.

1. App Operator Tasks

   - Create the service `ClassClaim` to be consumed by your workload that references your postgres instance:

      ```bash
      $ tanzu service class-claim create customer-database --class postgresql-unmanaged -n <workload-namespace>
      ```

2. App Developer Tasks

   Now that we have the database instance and class claim configured, we can check the database state by running:
   
   ```bash
   tanzu services class-claims get customer-database -n <workload-namespace>
   ```

   Make sure the claim status is "Ready".
   
   As soon as the database claim is ready, the `Workload` can be deployed.
