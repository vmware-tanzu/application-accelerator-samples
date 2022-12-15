# Tanzu Application Platform Maven Wrapper Fragment

A Fragment for adding a Maven build wrapper.

## Using the fragment

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`:

```yaml
accelerator:

# ...

  imports:
  - name: build-wrapper-maven
```

Then in your `engine` section add an `InvokeFragment` transform:

```yaml
engine:
  merge:

    # this is where your original transforms go
    
    - type: InvokeFragment
      reference: build-wrapper-maven
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: build-wrapper-maven
  namespace: accelerator-system
spec:
  displayName: Include Maven wrapper
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/build-wrapper-maven
```
