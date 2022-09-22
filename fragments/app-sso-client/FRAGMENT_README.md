# AppSSO Client Registration Fragment

A Fragment that include AppSSO Client Configuration and Workload reference into your Host Accelerator. This enables
a Workload to support authentication delegated to an AppSSO AuthServer.

It generates `client-registration.yaml` and related `client-registration-claim.yaml` to be created at your workload namespace.

It changes your `workload.yaml` to reference the Client Registration Claim: this will bind the Client Registration into your workload.

> Currently `workload.yaml` modification works ONLY if there is no other service claims defined in it!

## Using the fragment

This fragment requires the following parameters:
- `workloadName`
  this is the reference to the Tanzu Application Platform Workload
- `workloadURL`
  As AppSSO needs to know upfront which redirect-url are valid, this URL is used for generating a valid redirection URL in `client-registration.yaml`
- `appSsoServerLabel` which defines root package of an application to be generated. If your accelerator doesn't have this option you can include it by importing this fragment in your `accelerator.yaml`
  The ClientRegistration will be registered at one of the AppSSO instances at your environment. Please specify the metadata label to uniquely identify one. Refer to the deployed AuthServers in your cluster.

To include this fragment you should add an import to the `accelerator` section. This import will also add all above options to the UI.

```yaml
accelerator:

# ...

  imports:
  - name: app-sso-client
```

If you accelerator already has any of these options but with a different name you can redefine it through a `let` directive
at the place where this fragment is invoked (see yaml snippet bellow).

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level. See an example below.

```yaml
engine:
  #...
    - type: InvokeFragment
      reference: app-sso-client
      let:
        - name: workloadName
          expression: "#applicationName"
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: app-sso-client
  namespace: accelerator-system
spec:
  displayName: "AppSSO Client Registration Fragment"
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/app-sso-client
```
