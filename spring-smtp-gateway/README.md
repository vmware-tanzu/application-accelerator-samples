# spring-smtp-gateway

## Deployment Guide

Below are detailed deployment instructions for various platforms.  Use these pages to quickly get the applications installed and running.

* [Tanzu Application Platform](doc/TAPDeployment.md)
* Tanzu Application Services (TBD)
* Azure Spring Apps Enterprise (TBD)


## Description

The Spring SMTP Gateway is a set of applications that implement a lightweight SMTP server (`smtp-gateway`) which sends incoming RFC822 compliant messages over a Spring Cloud Stream destination to an arbitrary downstream processor application.  The processor application in this repo (`smtp-sink`) reads messages from the stream and dumps the full RFC822 message (including messages headers) to the standard out.

## Value Proposition

This set of sample applications and associated application accelerator serves the following purposes:

* Demonstrates use of the Tanzu Application Platform [server](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/workloads-server.html) and [worker](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/workloads-worker.html) workload types.
* Illustrates how to use the [Kubernetes Service Bindings Specification](https://github.com/servicebinding/spec/blob/main/README.md) along with the Tanzu Application Platform [services toolkit](https://docs.vmware.com/en/Services-Toolkit-for-VMware-Tanzu-Application-Platform/0.9/svc-tlk/overview.html).


## Connectivity Configuration

The SMTP server is pre-configured to listen on TCP port 1026, but can be overridden using the following Spring configuration property:

```
smtpmqgateway.binding.port
```

The server is also pre-configured for a maximum messages size of 39845888 bytes and a maximum header size of 262144 bytes.  These can be overridden with the following properties:

```
smtpmqgateway.message.maxHeaderSize
smtpmqgateway.message.maxMessageSize

```

If you wish to limit the IPs that can connect to the server, a configuration option is available for an `allow list` of CIDR ranges that can connect to the server.  CIDR ranges are comma delimited.  The following is an example of a configured list of CIDR ranges:

```
smtpmqgateway.clientsafelist.cidr=127.0.0.1/16,192.168.0.1/16
```

## Spring Cloud Streams Configuration

The applications services are pre-configured to use a RabbitMQ binding and by default attempt to connect to `localhost:5672` with a username\password of `guest\guest`.  These can be overriden using standard [Spring configuration properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.integration) for RabbitMQ.  When used along side with Kubernetes `service binding` and the `spring cloud bindings` library, binding properties will be "injected" into the container using `workload projection`, be available as Kubernetes secrets, and be converted into appropriate RabbitMQ Spring properties.

The accelerator contains a selection to optionally use Kafka instead of RabbitMQ.


## Messaging System

The application services requires a messaging system to function properly.  RabbitMQ and Kafka are currently supported.

### Connectivity Configuration

The application services do not care where or how the messaging system is installed; they just need configuration information to connect to the cluster.  As described earlier in this document, the configuration is done through Spring configuration properties, and there are many options to provided those properties.

#### Kubenetes Service Binding

If you are using Tanzu Application Platform or some other build system like `pack` or `kpack` to build a container, these system will automatically include the spring service binding library in the image.  The service binding library looks for mounted secrets in the container file system at runtime to obtain the necessary connectivity information.  Depending on your platform, those secrets are mounted using your platforms implementation of the service binding specification.


## Tanzu Application Platform Accelerator

This repository includes an `accelerator.yaml` file that is used by the Tanzu Application Platform [Accelerator](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/application-accelerator-about-application-accelerator.html) feature.  Using this repository as the backing for an accelerator, you can generate project and configuration files to facilitate connecting to necessary data services (eg. RabbitMQ or Kafka) and building/deploying the application services.

### Configuration Options

The accelerator contains the following configuration options:

* **SMTP Gateway Container Port:**  The port that the smtp-gateway micro-service will be listening on for SMTP connections and SHOULD match the application's port configuration.  The default port is 1026 which is the same default port that the application listens on.
* **SMTP Gateway Service Port:** The port that the Kubernetes service resource will be listening on.
* **Workload Namespace:** The namespace where the application micro-services will be deployed.  It is assumed that this namespace has already been created.
* **Messaging Service Type:** The messaging system that the application will expect to connect to.  Options are RabbitMQ and Kafka.
* **Class Claim Name:** The name of the class claim used to connect to messaging system.

The generated zip file from the accelerator will contain project folders for all micro-services.  It will also contain updated workload.yaml files that contain configuration data from the choices above.

### Application Deployment

Before deploying the application, an appropriate persona (potentially a developer) should first deploy the messaging system.  TAP includes an out-of-the-box 
'ClusterInstanceClass' and supporting CrossPlane configuration for creating a RabbitMQ or Kafka instance via the Tanzu CLI.  

Run the appropriate command below to create a messaging system isntance that matches your selection in the accelerator options.


RabbitMQ:

```
tanzu service  class-claim  create  msgbroker-spring-smtp-gateway  --class rabbitmq-unmanaged
```

Kafka:

```
tanzu service  class-claim  create  msgbroker-spring-smtp-gateway  --class kafka-unmanaged
```

Each project folder contains a `workload.yaml` file under its `config` folder.  If you are using the `tanzu cli`, you can use these files to build and deploy the micro-services.

Ex:

```
tanzu apps workloads create -f ./config/workload.yaml
```

Optionally, the accelerator creates a `workloads.yaml` in the `config/	` directory that can be used to deploy all micro-services.  This can be applied by a developer persona using the `kubectl` command.

```
kubectl apply -f ./config/workloads.yaml
```

## Testing the Deployment

Assuming the application has successfully deployed, you can test the application by using the `telnet` (or something else which can
speak SMTP) application and kubectl port forwarding.

In one command shell, run the following command substituting the local port with a port of you choosing and the container port and workload namespace from your selections in the accelerator.

```
kubectl port-forward deploy/smtp-gateway <local_port>:<container_port> -n <workload_namespace>
```

Once port forwarding is in place, you can use the the telnet command below in another command shell (substituting the local port with the local port in the previous step).

```bash
cat <<EOF | telnet localhost <local_port>
ehlo console
mail from: ea@vmware.com
rcpt to: gm@vmware.com
data
To: Evan Anderson <ea@vmware.com>
From: Greg Meyer <gm@vmware.com>
Subject: Application Test
MIME-Version: 1.0
message-id: 0c796d0e-4c76-43e8-be40-2cd5e30c1006
Date: Mon, 23 May 2023 07:57:27 -0500

Hello world!  I made it here.
.
quit
EOF
```

You can then check that the smtp-sink application received the message with the following command:

```
kubectl logs -l app.kubernetes.io/component=run,carto.run/workload-name=smtp-sink --tail 20 -n <workload_namespace>
```
