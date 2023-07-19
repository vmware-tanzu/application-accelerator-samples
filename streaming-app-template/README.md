# Streaming App Template

## Description

The Streaming App Template is an application accelerator for quickly generating a starter application that implements aspects of streaming application
using Spring Cloud Streams concepts.  The created application can take on one of more the following traits of a streaming pattern:

- Source: Source application that generates new events
- Processor: Processing applications that perform business logic on events from the sources and sends them onto a sink
- Sink: Sink application that persists of performs some type of final operation of processed events.

The generated application contains source code and configuration needed for each trait as well as an default event model object.

## Usage

The application accelerator generates a starter project based on selected configuration options.  These options include:

* **Name:**  The name of the workload/project that will be created.  The spring.application.name will also reflect this option.
* **Maven Group Name:** The maven group name used for a build application.
* **Service Root Package Name:**  The base Java package name used for the application.
* **Application Main Class Name:**  The name of class that will be used as the SpringBoot main class.
* **Model Class Name:**  The name of class that will be used for the model object that implements the event passed between services.
* **Event Source:** If this option is checked, the application will contain boilerplate code and configuration to generate events using a default `PollingBean`
* **Event Processor:** If this option is checked, the application will contain boilerplate code and configuration to process an event using a `Function`.
* **Event Sink:** If this option is checked, the application will contain boilerplate code and configuration to store or perform final processing using a `Consumer`.
* **Event Source Channel:** If the application is an event source or processor, this is the name of the messaging channel for the source event object. 
* **Event Source Channel Group:** If the application is an event processor, this is the name of the messaging group for the source event object. 
* **Event Result Channel:** If the application is an event processor or sink, this is the name of the messaging channel for the processing result object. 
* **Event Result Channel Group:** If the application is an event sink, this is the name of the messaging group for the processing result object. 
* **Message Broker Name:** The name of the message broker that will be used for service binding.  This is generally the name of a `ClassClaim`. 

## Project Layout

The generated application will have the following layout; function classes will be generated based on selected applications roles:

```
pom.xml
 + - src/main/java
     + - resources
         | application.yaml
     + - <package root>
         | - <MainApplciationClass>.java  
         + - functions
             | - <Model>Process.java
             | - <Model>Source.java
             | - <Model>Sink.java
             + - model
                 | <EventModelClass>.java
```

## Application Function

By default, the source application generates a new event using a `PollableBean` method that simply return a new instance of the event model class (by default, a new event is
generated every second).  The processor simply logs out message that the event was received and returns the event unmodified.  Lastly, the sink outputs that the 
processor result was received.

## Application Creation

In many instances a streaming application will only play single role of either a source, processor, or sink.  You can use this accelerator to generate a project that plays
one ore more roles, but typically you would create an application per role.  An example workflow would be:

* Create a source application setting the `Event Source` channel, `Event Model`, and `Message Broker Name`
* Create another application using the same settings but choosing `Event Processor` instead of `Event Source` and adding a setting for the `Event Source Group` channel group and `Event Result` channel.  You also need to change application name.
* Create another application using the same settings but choosing `Event Sink` instead of `Event Processor` and adding a setting for the `Event Result Group` channel.  You also need to change application name.

## TAP Deployment Guide

The streaming sample connects to a RabbitMQ broker for sending and receiving messages.  You will need to create a `ClassClaim` that matches the name of the 
`Message Broker Name` option.  To create the `ClassClaim` as well as a RabbitMQ instance, run the following command updating the <NAME> placeholder with the name
of the message broker and the <WORKLAOD_NAMESPACE> with the name of the namespace where the application will be deployed.

```
tanzu service  class-claim  create  <NAME>  --class rabbitmq-unmanaged -n <WORKLAOD_NAMESPACE>
```

After the `ClassClaim` is created, you can build and deploy the application by navigating to the root directory of the accelerator generate project and run the following 
command:

```
tanzu apps workload create -f config/workload.yaml --local-path .
```

## Testing

This testing scenario assumes that you have deployed the application with all three roles enabled.  This could be done in a single application, but typically will be spilt
out into three seperate application performing a specific streaming role.

The source application will emit a message everyone one second.  View the logs of the source application and confirm that you see a message similar to the following:

```
Supplier generating new com.example.tanzu.streamtemplate.model.Foo
```

The processor application will receive the message and pass it along unmodified to the sink.  View the logs of the source application and confirm that you see a message 
similar to the following:

```
Processing incoming com.example.tanzu.streamtemplate.model.Foo
```


The sink application will receive the processed message and perform a no-op.  View the logs of the sink application and confirm that you see a message 
similar to the following:

```
Storing incoming com.example.tanzu.streamtemplate.model.Foo in sink
```