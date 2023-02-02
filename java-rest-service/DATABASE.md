# Configuring a database

## Local with PostgreSQL

[local]

> Note: If you start the application locally please be sure that `local` profile is active.

## Using PostgreSQL as the database with Tanzu Application Platform (TAP) and VMware Tanzu SQL

Using the `config/workload.yaml` it is possible to build, test and deploy this application onto a
Kubernetes cluster that is provisioned with Tanzu Application Platform (https://tanzu.vmware.com/application-platform).

As with the local deployment a PostgreSQL instance needs to be available at the cluster.
When using [VMware Tanzu SQL with Postgres for Kubernetes](https://docs.vmware.com/en/VMware-Tanzu-SQL-with-Postgres-for-Kubernetes/index.html),
you would typically need to ask a service operator to provision an instance for you to use. For this sample we do include the configuration files that a service operator could use.

Also, see [VMware SQL](https://tanzu.vmware.com/sql) for more information about the VMware SQL offerings.

> Note: The Tanzu Postgres Operator must be installed in the cluster.

> Note: please verify the storage class to be used for the PostgreSQL storage. You can run `kubectl get storageclasses` to get a list of the ones available in your cluster.

When the PostgreSQL instance is created, resource binding needs to be configured in order that your workload can access
the PostgreSQL instance. All provided configuration files for this sample assume that the database will be running in a `service-instances` namespace which is different from the namespace where you deploy your app.

1. Service Operator Tasks

    > Note: This sample uses `postgres-1` as the instance name. If that is not available to use, then the any reference to this name must be updated to something that is available for use.

   - If the `service-instances` namespace doesn't exist, then as a service operator, you can run the following command to create it.

      ```bash
      $ kubectl apply -f config/service-operator/service-instances-namespace.yaml
      ```

   - If the `ClusterRole` for service binding with postgres resources has not been defined, then as a service operator, you can run the following command.

      ```bash
      $ kubectl apply -f config/service-operator/postgres-cluster-role.yaml
      ```

   - If the `ClusterInstanceClass` for postgres has not been defined, then as a service operator, you can run the following command.

      ```bash
      $ kubectl apply -f config/service-operator/postgres-cluster-instance-class.yaml
      ```

   - If the `ResourceClaimPolicy` to define which namespaces could have access to your instance namespace, then as a service operator, you can run the following command.

      > Note: You can edit `consumingNamespaces` in `config/service-operator/postgres-resource-claim-policy.yaml` to only contain your workload namespace if necessary, it defaults to '*' for all namspaces.
      
      ```bash
      $ kubectl apply -f config/service-operator/postgres-resource-claim-policy.yaml
      ```

   - Create an instance of the PostgreSQL database by running the following command.

      ```bash
      $ kubectl apply -f config/service-operator/postgres-service-instance.yaml
      ```

2. App Operator Tasks

   - Create the `ClassClaim` to be consumed by your workload that references your postgres instance:

      ```bash
      $ kubectl apply -n <your-workload-namespace> -f config/app-operator/postgres-class-claim.yaml
      ```

3. App Developer Tasks

   Now we have the database instance and class claim configured we can check the database state by running:
   
   ```bash
   kubectl get postgres -n service-instances
   ```

   Make sure the database status is "Running".
   
   You can also check the state of the class claim by running:
   
   ```bash
   kubectl get classclaim -n <your-workload-namespace>
   ```
   
   The class claim should be "Ready".

   As soon as the database is running the `Workload` can be deployed.
