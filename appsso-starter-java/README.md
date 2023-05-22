# AppSSO Starter Java

This repository provides an example application used to set up an authentication mechanism with AppSSO.

## Getting Started

1. Discover your `AppSSO` service offerings

   ```bash
   tanzu services classes list
   ```

   If there isn't one,

   - ask your service operator `OR`
   - [create your own `ClusterUnsafeTestLogin`](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.6/tap/app-sso-reference-api-clusterunsafetestlogin.html)

1. Select the service offering you'd like to connect

1. Claim an instance from the offering

   ```bash
   tanzu services class-claims create appsso-starter-java \
     --class <your-selected-appsso-offering> \
     --parameter workloadRef.name=appsso-starter-java \
     --parameter redirectPaths='["/login/oauth2/code/appsso-starter-java"]'
   ```

1. Apply your workload

   ```bash
   kubectl apply -f config
   ```

For reference, see
[AppSSO's documentation](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.6/tap/app-sso-about.html)
