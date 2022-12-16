# Namespace Provisioner Resources

## Namespace provisioner

Namespace provisioner for Tanzu Application Platform provides an easy, secure, automated way for Platform Operators to provision namespaces with the resources and proper namespace-level privileges required for their workloads to function as intended.


## Resources

Here you can find examples which show how to extend and/or customize the namespace-provisioner default resources.

### Extend the OOTB default-resources

Platform Operators may need to do additional namespace customization beyond TAP requirements (such as quota allocation or creation of other namespaced resources) that are not taken care of by the Out of the Box *default-resources* Secret. Platform Operators can add additional git sources in *tap-values* configuration of the namespace provisioner. This allows the Platform Operators to extend the out of the box setup with additional resources which helps them achieve the bespoke customization that they need for their requirement.

#### Custom resources

- [Random](./custom-resources/random/data-values-configmap.yaml):  how to add a particular `ConfigMap` using Carvel-YTT *yaml* and *data* modules
- [Scan policies](./custom-resources/scanpolicies/snyk-scanpolicies.yaml): how to add a `scanpolicy.scanning.apps.tanzu.vmware.com` for *snyk* if the `data.values` contains the key `scanpolicy` and the value is snyk`
- [Tekton pipelines](./custom-resources/tekton-pipelines): how to create `pipeline.tekton.dev` for `angular`, `python` or `golang` if the `data.values` contains the key `language` and the value is the desire language
- [Testing scanning supplychain](./custom-resources/testing-scanning-supplychain/tekton-pipeline-java.yaml): how to create the `scanpolicy.scanning.apps.tanzu.vmware.com/v1beta1` and the `pipeline.tekton.dev` for java (gradle/maven) projects as referenced in [Install OOTB Supply Chain with Testing and Scanning](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/GUID-getting-started-add-test-and-security.html#install-ootb-supply-chain-with-testing-and-scanning-5)
- [Workload sa](./custom-resources/workload-sa/workload-sa-with-secrets.yaml): how to create custom `Secret`s, `ServiceAccount`s and `RoleBinding`s


### Customize OOTB default-resources

The Out-Of-The-box *default-resources* can be customized by using GitOps with some specific characteristics

- The GitOps customization should be done by using the ytt overlay feature and should be set in the *tap-values* under additional_resources.
- The additional git resource should be mounted in the *path* `_ytt_lib/customize`, otherwise the customization will not be considered
- The GitOps repo folder should have a file with an extension `.lib.yaml` to be recognized as a ytt library with members to be exported
- The library file in the GitOps repo folder should have a function called *customize* with the overlays to be applied to the resources, it can contain 1 or more overlays

#### Default resources overrides

- [Overlays](./default-resources-overrides/overlays/sa-secrets.lib.yaml): how to change the `secrets` and `imagePullSecrets` sections of the *default* `ServiceAccount` created by the namespace provisioner

### Control the desired-namespace ConfigMap via GitOps

Users can choose to maintain the *desired-namespaces* `ConfigMap` in their git repository and opt not to use the *namespace-provisioner* controller. Users can use their choice of GitOps tool to override the *desired-namespaces* `ConfigMap` in the *tap-namespace-provisioning* namespace.

***NOTE:*** you need to set the *controller* tap value key to “false” (Default is “true”) for namespace provisioner in tap-values.yaml file

#### Desired-namespaces

- [desired-namespaces](./desired-namespaces/gitops-managed-desired-namespaces.yaml):  How of how the *desired-namespaces* `ConfigMap` can be set via GitOps, it needs to keep unchanged the *name*, the *namespace* and the *annotation* `namespace-provisioner.apps.tanzu.vmware.com/no-overwrite: ""` (This annotation tells the provisioner app to not override this configMap as this is the desired state.)
