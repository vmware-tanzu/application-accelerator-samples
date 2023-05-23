# AppSSO Client Registration Fragment

A Fragment that include AppSSO Client Configuration and Workload reference into your Host Accelerator. This enables
a Workload to support authentication delegated to an AppSSO AuthServer.

It changes your `workload.yaml` to reference the ClassClaim: this will bind your client to the single sign on service in the cluster.

## Using the fragment

This fragment requires the following parameters:
- `workloadName`
  this is the reference to the Tanzu Application Platform Workload

To include this fragment you should add an import to the `accelerator` section. This import will also add all above options to the UI.

```yaml
accelerator:

# ...

  imports:
  - name: app-sso-client
```

If your accelerator already has any of these options but with a different name you can redefine it through a `let` directive
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
