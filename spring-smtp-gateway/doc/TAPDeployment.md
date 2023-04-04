# Tanzu Application Platform (TAP) Deployment

The SMTP server deployment options are enabled through a Tanzu application accelerator that will generate all necessary configuration for you.  

## Prerequisites

These instructions assume that you have a TAP 1.2.x or greater iterate cluster (or some variant similar to an iterate cluster) up and running with the following packages installed and [kubectl](https://kubernetes.io/docs/tasks/tools/) and the Tanzu CLI installed and configured to access your TAP cluster:

* Tanzu TAP GUI
* Tanzu Build Services
* Tanzu Cloud Native Runtimes
* Tanzu Service Bindings
* Tanzu Services Toolkit
* Tanzu Out of the Box Supply Chains
* Tanzu Out of the Box Templates
* Tanzu Source Controller
* Bitnami Services
* CrossPlane


## Quick Start

This section provides a fast track installation using the application accelerator and the instructions immediately below.  This section assumes you have already installed the application accelerator using the instructions at the top of the page.

* Deploy RabbitMQ Instance:

```
tanzu service  class-claim  create  msgbroker-spring-smtp-gateway  --class rabbitmq-unmanaged
```

* Navigate to your TAP GUI web page and Application Accelerator tab on the left of the screen.  Select the `Choose` button on the `Spring SMTP Gateway` Application

* Select all defaults except change the `workload-namespace` if need be to a namespace that you have already configured to run workloads (e.g. a developer namespace).  Download and unzip the generate accelerator file to you workstation.

* Open a command shell and navigate to the root directory of the unzipped file from above.  Run the following commands to create a RabbitMQ instance, resource claims, and workloads:

```
kubectl apply -f ./config/
```

Depending on previously installed/cached components, network speed/latency, and available cluster compute, the amount of time for the RabbitMQ cluster to spin up and the workloads to build and deploy may vary greatly.  It is possible for the process to take more than 10 minutes in some instances.
