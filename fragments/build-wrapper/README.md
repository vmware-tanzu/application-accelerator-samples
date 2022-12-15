# Tanzu Application Platform Build Tool Wrapper Fragment

A Fragment for adding a build tool wrappers.

## Using the fragment

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`:

```yaml
accelerator:

# ...

  imports:
  - name: build-wrapper
```

Then in your `engine` section add an `InvokeFragment` transform passing in the name of the `buildTool` option variable
to be used, valid values are 'maven' and 'gradle':

```yaml
engine:
  merge:

    # this is where your original transforms go
    
    - type: InvokeFragment
      let:
      - name: buildTool
        expression: "maven"
      reference: build-wrapper
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: build-wrapper
  namespace: accelerator-system
spec:
  displayName: Include build tool wrapper
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/build-wrapper
```
