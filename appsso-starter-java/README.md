# AppSSO Starter Java

This repository provides an example application used to set up an authentication mechanism with AppSSO.

## Usage

See ["Securing your first Workload" tutorial](https://docs.vmware.com/en/Application-Single-Sign-On-for-VMware-Tanzu/1.0/appsso/GUID-app-operators-tutorials-index.html)
on how to use this accelerator.

## Tips

In order to succesfully deploy this sample, you need to perform the following steps:

- [Provision an AuthServer](https://docs.vmware.com/en/Application-Single-Sign-On-for-VMware-Tanzu/1.0/appsso/GUID-getting-started-provision-auth-server.html)
    - Provide `issuerURI` that works for the shared doamin used for your cluster
- [Expose your authorization server through HTTPProxy](https://docs.vmware.com/en/Application-Single-Sign-On-for-VMware-Tanzu/1.0/appsso/GUID-getting-started-expose-httpproxy.html)
    - Set the `spec.virtualhost.fqdn` to match your `issuerURI` provided above
- [Deploying the sample application as a Workload](https://docs.vmware.com/en/Application-Single-Sign-On-for-VMware-Tanzu/1.0/appsso/GUID-app-operators-tutorials-index.html#deploying-the-sample-application-as-a-workload-4)
