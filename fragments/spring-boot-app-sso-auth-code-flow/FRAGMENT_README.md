# Spring Boot AppSSO Authorization Code Flow Fragment

A Fragment that includes AppSSO OIDC Authorization Code Flow into your Spring Boot based Accelerator. This enables
a Spring Boot application to support authentication delegated to an AppSSO AuthServer.

It adds Spring Security OAuth 2.0 and Thymeleaf dependencies on your `pom.xml` or `build.gradle.kts`.
It includes `WebSecurityConfiguration` class that configures Spring Security to use OAuth 2.0.

It includes an additional Spring MVC Endpoint at `/protected/profile` to trigger the authorization flow and, when successful,
show the logged-in subject.

> This fragment can be used together with `app-sso-client` which configures the Tanzu Application Platform workload.yaml
> to bind the ClientRegistration claim. This way Spring Boot is able to find the OAuth 2.0 Client Registration configuration at run-time.

> If you start a generated application locally please be sure that `local` profile is active. 

## Using the fragment

To include this fragment you should add an import to the `accelerator` section:

```yaml
accelerator:
  # ...
  imports:
  - name: spring-boot-app-sso-auth-code-flow
    
```

This fragment requires one parameter `packageName` which defines root package of an application to be generated. 
If your accelerator has this option with a different name, you can redefine with using a `let` directive at the place
where this fragment is invoked.

Then in your `engine` section add an `InvokeFragment` directive at an appropriate level.

```yaml
engine:
  # ...
    - type: InvokeFragment
      reference: spring-boot-app-sso-auth-code-flow
      let:
        - name: packageName
          expression: "#applicationRootPackage"
```

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: spring-boot-app-sso-auth-code-flow
  namespace: accelerator-system
spec:
  displayName: "Spring Boot AppSSO Authorization Code Flow Integration"
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/spring-boot-app-sso-auth-code-flow
```
