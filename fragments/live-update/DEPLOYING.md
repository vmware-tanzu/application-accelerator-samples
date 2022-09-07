### Deploying to Kubernetes using VMware Tanzu Developer Tools for Visual Studio Code

You can open this project using Visual Studio Code. Make sure you have [VMware Tanzu Developer Tools for Visual Studio Code](https://docs.vmware.com/en/Tanzu-Application-Platform/1.0/tap/GUID-vscode-extension-about.html) installed.

For best results use Tilt version v0.23.2 or later. You can install Tilt by following these instructions: https://docs.tilt.dev/install.html

Follow [instructions in the documentation](https://docs.vmware.com/en/Tanzu-Application-Platform/1.0/tap/GUID-vscode-extension-usage-iterating-on-app.html) for deploying your app for live update and debugging.

These are the basic steps to use live update:

1. In Visual Studio Code, navigate to `Preferences > Settings > Extensions > Tanzu`.
    - In the `Source Image` field, provide the destination image repository to publish an image containing your workload source code. This should match what is specified for `SOURCE_IMAGE` default in the Tiltfile.
1. From the Command Palette (⇧⌘P), type "Tanzu", and select `Tanzu: Live Update Start`. You can view output from Tanzu Application Platform and from Tilt indicating that the container is being built and deployed.
    - You see "Live Update starting..." in the status bar at the bottom right.
    - Live update can take 1-3 minutes while the workload deploys and the Knative service becomes available.
        > Note: Depending on the type of cluster you use, you might see an error similar to the following:
        ```
        ERROR: Stop! cluster-name might be production.
        If you're sure you want to deploy there, add:
                allow_k8s_contexts('cluster-name')
        to your Tiltfile. Otherwise, switch k8s contexts and restart Tilt.
        ```
        Follow the instructions and add the line "allow_k8s_contexts('cluster-name')" to your Tiltfile.
1. When the Live Update status in the status bar is visible, resolve to "Live Update Started", navigate to http://localhost:8080 in your browser, and view your running application.
1. Make changes to the source code. When the codes is saved the running application will get updated.
1. Either continue making changes, or stop the live update when finished. Open the command palette (⇧⌘P), type "Tanzu", and select `Tanzu: Live Update Stop`.

### Deploying to Kubernetes as a TAP workload with Tanzu CLI

If you make modifications to the source and push to your own Git repository, then you can update the `spec.source.git` information in the `config/workload.yaml` file.

When you are done developing your function app, you can simply deploy it using:

```
tanzu apps workload apply -f config/workload.yaml
```

If you would like deploy the code from your local working directory you can use the following command:

```
tanzu apps workload create my-project -f config/workload.yaml \
  --local-path . \
  --source-image dev.local/my-project-source \
  --type web
```

### Accessing the app deployed to your cluster

Determine the URL to use for the accessing the app by running:

```
tanzu apps workload get my-project
```

To access the deployed app use the URL shown under "Workload Knative Services".

This depends on the TAP installation having DNS configured for the Knative ingress.
