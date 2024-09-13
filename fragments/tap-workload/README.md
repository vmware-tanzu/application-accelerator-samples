# Tanzu Application Platform Workload Git Repository Fragment

A Fragment that sets the Git Repository location of the Workload. This special Fragment is used by the Engine to support automatically creating
Git repositories on behalf of the user.

It changes your `workload.yaml` to contain the source of the generated application.

## Using the fragment

This fragment requires the following parameters:
- `bsGitRepository`
  a construct, normally provided by the engine, containing public host of Git provider, owner and repository. For example scheme, host, owner.
  `github.com?owner=your-organization&repo=java-rest-service-repo`.
  > Note: HTTPS scheme will be used to read from your repository. Be sure that your TAP Installation has read access.
- `bsGitBranch`
  the branch to use, for example: `main` or `feature/first-iteration`

> As it is used by the Accelerator system itself, it needs to be available, but not recommended to use it from a self-authored Accelerator. 

## Creating the fragment resource

To create this fragment use:

```yaml
apiVersion: accelerator.apps.tanzu.vmware.com/v1alpha1
kind: Fragment
metadata:
  name: tap-workload
  namespace: accelerator-system
spec:
  displayName: "Tanzu Application Platform Workload Git Repository Fragment"
  git:
    ref:
      branch: main
    url: https://github.com/vmware-tanzu/application-accelerator-samples.git
    subPath: fragments/tap-workload
```
