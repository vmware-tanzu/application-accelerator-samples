Dev Container
=============

This project comes with a `.devcontainer.json` file. This enables the provisioning of a 'development envinment'
inside a local docker container. This sets up the tools you need to work with your Tanzu cluster:

- `kubectl` CLI
- `tanzu` CLI
- Java JDK
- Common cloud-provide CLIs (Google, AWS and Azure)
- The [Tanzu Developer Tools](https://marketplace.visualstudio.com/items?itemName=vmware.tanzu-dev-tools) extension for Vscode.

Vscode provides support for `.devconainer.json`, but to use it you must install 
the [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers) 
extension from Vscode marketplace.

After installing the extension, when you open this project in Vscode you will be prompted "Reopen in a Dev Container".

Accept the prompt and Vscode will restart; and start building a Dev Container docker image based on the 
`.devcontainer.json` specification. This may take several minutes the first time, but once the container exists
it should be re-used the next time you work on the project.

## Connecting to your Cluster

When you start working in your devcontainer for the first time, you will need to login and connect to your
Tanzu kubernetes cluster. The way you do that exactly will vary depending on your cloud provider. For example,
for Google Cloud you'd run a command similar to this:

```
gcloud container clusters ${your_cluster_name} --region ${your_region} --project ${your_google_cloud_project_name}
```

Refer to the documentation of your specific cloud provider for detailed information on how to connect to a cluster.

Once you logged into the cluster you will need to Restart the IDE for it to become aware of the new
cluster connection. You can do this by pressing CTRL-SHIFT-P (or CMD-SHIFT-P on Mac) and choosing 
"Developer: Reload Window" in the command palette.

## Tanzu CLI EULA and other Legal Requirements

Notice that in the `.devcontainer.json` you can find the following:

```
"projects.registry.vmware.com/tanzu-developer-containers/features/vmware-tanzu-dev-tools": {
    "acceptEula": true, // <-- assumes you accepted Tanzu EULA (needed to run TAP)
    "acceptCeip": false // <-- See https://www.vmware.com/solutions/trustvmware/ceip.html
}
```

This installs `tanzu` cli (amongst other things) into your devcontainer. To use the 
tanzu cli you need to accept the Tanzu EULA and make a choice about CEIP participation.

The options indicate that: 

1. `"acceptEula": true` means you accept the Tanzu CLI EULA. We assume you must have accepted the EULA 
   already since this project was created using an accelerator.

2. `"acceptCeip": false` means you did not agree to participate in 
   [Tanzu CEIP Program](https://www.vmware.com/solutions/trustvmware/ceip.html).
   The accelerator that generated this project has no way to know whether or not you have already 
   opted in to this program. Therefore we have conservatively set it to `false` so as to avoid collecting
   any data without your explicit permission. If you did want to participate in the program
   you can change that value to `true` instead.