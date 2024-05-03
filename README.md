# Spaces - GA Sample App

This repository contains multiple flows for building and deploying the Where For Dinner application into a various `Spaces` using
constructs shipped with the TAP SaaS GA release specifically using the `tanzu build` and `tanzu deploy` CLI command.  
For several of the flows, it is assumed that the target `Spaces` have already been provisioned with the proper profiles and traits, 
however, future flows may include the provisioning of a `Space` as part of the deployment process.

The following flows are currently supported by this repository:

* Initialize (TBD): Walks through the process of initializing the application and workloads with build and deployment configuration.
* [Build/Deploy](doc/buildDeploy.md): Builds and deploys the application from an initialed set of workloads.
* [Deploy Pre-built](doc/preBuilt.md): Deploys the application from a set pre-built outputs.  
* [GitOps](gitOps.md): Deploys the application to various `Spaces` from a GitOps folder structure.

## Obtain The GA Repository

Before executing any of the flows above, you will to clone this repository by running the following commands

```
git clone https://github.com/vmware-tanzu/application-accelerator-samples
cd application-accelerator-samples
git checkout wfd-spaces-ga
cd where-for-dinner
```
