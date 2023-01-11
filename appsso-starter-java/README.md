# AppSSO Starter Java

This repository provides an example application used to set up an authentication mechanism with AppSSO.

## Usage

In order to successfully deploy this sample, you need to perform the following steps:

- [Provision an AuthServer](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/app-sso-getting-started-provision-auth-server.html)
- [Provision a ClientRegistration](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/app-sso-getting-started-client-registration.html)
- [Deploying the sample application as a Workload](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/app-sso-app-operators-tutorials-securing-first-workload.html)

### Generating a ClientRegistration

Using `ytt` CLI, to generate a `ClientRegistration` resource for this project, run:

```shell
ytt \
    --file client.yaml \
    --data-value namespace=workloads \
    --data-value workload_name=appsso-starter-java \
    --data-value domain=127.0.0.1.nip.io \
    --data-value authserver_label.my-sso=true \
    --data-value authserver_label.env=dev |
      kubectl apply -f -
```

where:

- `namespace` - the namespace in which your workloads will run
- `workload_name` - name of the workload
- `domain` - base domain name of your TAP cluster
- `authserver_label.{matching-label}` - a uniquely identifying label(s) for your authorization server. In this example,
  we assume that the `AuthServer` resource has labels `my-sso: "true"` and `env: dev`. You may add as many identifying
  labels as needed.

For more information, check
out [Deploying the sample application as a Workload](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.4/tap/app-sso-app-operators-tutorials-securing-first-workload.html).
