Dev Container
=============

This project comes with a `.devcontainer.json` file. This enables the provisioning of a 'development environment'
inside a local docker container. The devcontainer provides all the tools you need to work on your code and 
and interact with your Tanzu cluster:

- `kubectl` CLI
- `tanzu` CLI
- Java JDK
- Common cloud-provider CLIs (Google, AWS and Azure).
- The [Tanzu Developer Tools](https://marketplace.visualstudio.com/items?itemName=vmware.tanzu-dev-tools) 
  extension for Vscode.

## Pre-requisites:

- Vscode Dev Containers extension: This can be installed from [Vscode marketplace](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers).
- Docker (or Rancher): You need a container runtime. On Linux and Mac, we have tested with Docker. On 
  Windows, both Docker and Rancher have been tested.
- AMD architecture: The current alpha release of tanzu devcontainer does not yet support
  ARM (e.g. Apple M1 Silicon). 

## Starting a Container:

After installing the extension, open this project in Vscode and you will be prompted to 
"Reopen in a Dev Container?". Accept the prompt and Vscode will restart. The first time it
will build a Dev Container docker image based on the `.devcontainer.json` specification. 
Be patient, this may take several minutes (the first time), but once the container is created
it should be re-used the next time you work on the project.

## Connecting to your Cluster

When you start working in your devcontainer for the first time, you will need to login and connect to your
Tanzu kubernetes cluster. The way you do that exactly will vary depending on your cloud provider. For example,
for Google Cloud you might run a command similar to this:

```
gcloud container clusters get-credentials ${your_cluster_name} --region ${your_region} --project ${your_google_cloud_project_name}
```

Refer to the documentation of your specific cloud provider for detailed information on how to connect to a cluster.

Once you logged into the cluster you will need to Restart the IDE for it to become aware of the new
cluster connection: press CTRL-SHIFT-P (or CMD-SHIFT-P on Mac) and choose 
"Developer: Reload Window" in the command palette.

You are now ready to start working on your code and deploy it to your cluster and monitor
your workloads in the Tanzu Panel. Refer to the documentation on [Tanzu Developer Tools](https://marketplace.visualstudio.com/items?itemName=vmware.tanzu-dev-tools) 
Extension for more details.

## Tanzu CLI EULA and other Legal Requirements

When building the devcontainer it installs `tanzu` cli, in order to use the CLI and its plugins you will have to review and accept [VMware General Terms](https://www.vmware.com/vmware-general-terms.html) the first time you use the Tanzu Developer Tools devcontainer feature. 

Similarly the first time you use the devcontainer feature you will be asked to participate in [Tanzu CEIP Program](https://www.vmware.com/solutions/trustvmware/ceip.html) and your choice will be preserved the next time you use the environment.