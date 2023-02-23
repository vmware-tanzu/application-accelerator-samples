# Spring Boot Database Fragment

A Fragment for adding testcontainers or an in-memory database capability to your Spring Boot application.

It will delegate to the following fragments:

- spring-boot-h2
- testcontainers-postgresql
- testcontainers-mysql


## Using the fragment

To include this fragment you should add an import to the `accelerator` section:

```yaml
accelerator:
  # ...
  imports:
  - name: spring-boot-database
    
```

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level.

```yaml
engine:
  # ...
    - type: InvokeFragment
      reference: spring-boot-database
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: spring-boot-database
  namespace: accelerator-system
spec:
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/spring-boot-database
```
