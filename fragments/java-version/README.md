# Java Version Fragment

A fragment for selecting the Java version to to build and run a generated project.

## Using the fragment

This fragment requires the following parameters:
- `javaVersion`
  this is the version of JDK to be used to build and run a generated application.

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`.
This import will also add all above options to the UI

```yaml
accelerator:

# ...

  imports:
  - name: java-version
```

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level. See an example below.

```yaml
engine:
  #...
    - include: [ "**" ]
    - type: InvokeFragment
      reference: java-version
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: java-version
  namespace: accelerator-system
spec:
  displayName: Select Java Version
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/java-version
```
