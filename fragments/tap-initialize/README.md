# Tanzu Application Platform Initialization Fragment

A Fragment for adding a TAP `workload.yaml` and `catalog-info.yaml` for a generated project. The `workload.yaml` added is of type
`web` without any references to services (like databases, message queues) or resource claims (like credentials).

## Using the fragment

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`:

```yaml
accelerator:
  imports:
  - name: tap-initialize
```

Then in your `engine` section add an `InvokeFragment` transform passing in the name of the `targetProject` option variable to be used as the artifactId for the fragment:

```yaml
engine:
  merge:

    # this is where your original transforms go
    
    - type: InvokeFragment
      let:
      - name: artifactId
        expression: "#targetProject"
      reference: tap-initialize
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: tap-initialize
  namespace: accelerator-system
spec:
  displayName: "TAP Initialize Web Workload"
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/tap-initialize
```
