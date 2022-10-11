# Spring Boot H2 Fragment

A Fragment for adding an in-memory database capability to your Spring Boot application. It will:
- Add dependency to either `pom.xml` or `build.gradle.kts`
- Add (or change) local profile properties (`application-local.properties`) to reference H2 in-memory database
- Add (or change) test profile properties (`application-test.properties`) to reference H2 in-memory database

## Using the fragment

To include this fragment you should add an import to the `accelerator` section:

```yaml
accelerator:
  # ...
  imports:
  - name: spring-boot-h2
    
```

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level.

```yaml
engine:
  # ...
    - type: InvokeFragment
      reference: spring-boot-h2
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: spring-boot-h2
  namespace: accelerator-system
spec:
  displayName: "Spring Boot H2"
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/spring-boot-h2
```
