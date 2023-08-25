# Devcontainer Fragment

A fragment for adding a `.devcontainer.json` file to a project. Currently it assumes
the project is Java based. 

This fragment reads options from other fragments to
adapt to them:

- `javaVersion` option from the `java-version` fragment is consulted to
   demernine which JDK to install in the container.
- `buildTool` and `includeBuildToolWrapper` options are consulted to decide whether 
  to include maven or gradle in the container. If `includeBuildToolWrapper` is
  true, it will include neither (it is assumed the user intends to use the wrapper
  instead of maven/gradle distro installed in the container). 

## Using the fragment

This fragment provides the following options:

- `includeDevContainer`: activates this fragment when set to `true` 
  (otherwise the fragment does nothing).

This fragment also reads the values of options that it doesn't provide 
itself (i.e. it is assumed these options will be set and provided by the inclusion of
other fragments, or by the calling accelerator itself)
- `javaVersion`this is the version of JDK to be used to build and run a generated 
   application. Typically it is provided by including/using the `java-version` fragment
   in conjunction with this fragment.
- `buildTool`: value should be either `maven` or `gradle`.  

To use this fragment in your accelerator add the following import under the `accelerator` section in your `accelerator.yaml`.
This import will also add all above options to the UI

```yaml
accelerator:

# ...

  imports:
  - name: java-version # <-- provides 'javaVersion' option 
  - name: devcontainer
```

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level. See an example below.

```yaml
engine:
  #...
    - type: InvokeFragment
      reference: build-tool
```

For a concrete example on how to use it, check the `tanzu-java-web-app` sample
accelerator.

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: devcontainer
  namespace: accelerator-system
spec:
  displayName: Include a `.devcontainer.json` file
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/devcontainer
```