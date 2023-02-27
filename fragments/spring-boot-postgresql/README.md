# Spring Boot PostgreSQL Fragment

## Using the fragment

To include this fragment you should add an import to the `accelerator` section:

```yaml
accelerator:
  # ...
  imports:
  - name: spring-boot-postgresql
    
```

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level.

```yaml
engine:
  # ...
    - type: InvokeFragment
      reference: spring-boot-postgresql
      let:
        - name: databaseIntegrationTestType
          expression: "'testcontainers'"
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: spring-boot-postgresql
  namespace: accelerator-system
spec:
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/spring-boot-postgresql
```
