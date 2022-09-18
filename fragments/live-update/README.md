# Tanzu Application Platform Workload Live Update Fragment

A Fragment for adding a TAP live-update enabled Tiltfile for a generated project.

## Using the fragment

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`:

```yaml
accelerator:

# ...

  imports:
  - name: live-update
```

Then in your `engine` section add an `InvokeFragment` transform passing in the name of the `targetProject` option variable
to be used as the artifactId for the fragment:

```yaml
engine:
  merge:

    # this is where your original transforms go
    
    - type: InvokeFragment
      let:
      - name: artifactId
        expression: "#targetProject"
      reference: live-update
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: live-update
  namespace: accelerator-system
spec:
  displayName: Live Update
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/live-update
```
