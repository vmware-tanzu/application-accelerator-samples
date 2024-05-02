# Dynamic Service Provisioning

The TAP dynamic service provisioning shifts the responsibility of deploying service instances from the service operator persona to the app operator and/or
developer personas.  Service operators are still responsible for making services like databases, message brokers, and caches available to development teams as 
well as determining policies around those services; however, the action of spinning up instances is now placed in the hands of the development team.  This
reduces the backlog of tickets placed on the service operators in that they should no longer need to respond to requests to create service instances.  At the
same time, this can accelerate a dev team's development life cycle by allowing them to be self sufficient in terms of service provisioning. 

Starting with TAP 1.5, the Where For Dinner accelerator supports the dynamic provisioning paradigm.  The accelerator creates configuration for three different 
possible service offerings:

* [Tanzu MySQL](https://docs.vmware.com/en/VMware-Tanzu-SQL-with-MySQL-for-Kubernetes/1.6/tanzu-mysql-k8s/GUID-index.html)
* [Tanzu](https://docs.vmware.com/en/VMware-RabbitMQ-for-Kubernetes/1.4/rmq/GUID-overview.html) or [OSS RabbitMQ](https://www.rabbitmq.com/kubernetes/operator/operator-overview.html)
* [Bitnami Redis](https://bitnami.com/stack/redis)

The default accelerator configuration creates `ClusterInstanceClass`es that reference [Crossplane configurations](https://docs.crossplane.io/v1.11/getting-started/) that are responsible
for creating instances of these services.  If your platform or service operators have configured additional `ClusterInstanceClass`es that can be used for dynamic provisioning, the
accelerator will allow you to change the default name of the `ClusterInstanceClass` that Where For Dinner will use for dynamic provisioning.

## Crossplane Configuration

The accelerator ships with Crossplane composite resource definitions(referenced as [XRD](https://docs.crossplane.io/v1.11/concepts/composition/#compositeresourcedefinitions)) 
and [compositions](https://docs.crossplane.io/v1.11/concepts/composition/#compositions) that are responsible for creating instances of the the services listed in the above section.
These resources are placed in the `config/service-operator` directory when the accelerator generates the project.  Previously, applying the resources in the `config/service-operator` 
directory would spin up instances of the data services.  But when using dynamic provisioning, the XRDs, compositions, and `ClusterInstanceClass`es simply define resources that will allow an app operator and/or developer capable of creating instances.  No instances of the services are created until the app operator configuration is applied.  


## Class Claims

The accelerator also ships with Tanzu services-toolkit `ClassClaim` resources that reference specific `ClusterInstanceClass`es.  If a `ClassClaim` references a `ClusterInstanceClass`
that is configured for dynamic provisioning, then the service instance will be created when the `ClassClaim` resource is applied to the cluster.  `ClassClaim`s are generally applied
to the cluster by app operators or/and or developers giving these personas the ability to create service instances on demand vs waiting for a service operator to create the instances.
`ClassClaims` are place in the `config/app-operator` directory when the accelerator generates the project.

The accelerator defaults the name of the `ClusterInstanceClass` referenced by a `ClassClaim` to point to one of the accelerator's out-of-the-box `ClusterInstanceClass` resources.  If a platform or service operator has defined other `ClusterInstanceClass`es, you can change the name of the referenced `ClusterInstanceClass` in the appropriate accelerator options.  The `ClaimClass` generated
by the accelerator will then reference this updated `ClusterInstanceClass`.

