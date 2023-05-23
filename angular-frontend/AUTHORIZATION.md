# Authorization

To leverage the benefits of Tanzu AppSSO `AuthServer`, there are a few things you should take into consideration.

1. **Ensure your `AuthServer` has the proper CORS policy configured**

   To enable a single page app with an AppSSO `AuthServer`, you will need to define allowed HTTP origins, as part of CORS. You can specify CORS settings in the `AuthServer` CR `spec.cors` section. Here are some examples:
  
    ```yaml
    kind: AuthServer
    spec:
      cors:
        allowOrigins:
          - http://127.0.0.1:4200/
    ```

   __Note:__ Replace the redirect URI with your `Workload`s URI
   uri with your `Workload`s URI

   Alternatively, to allow all origins, the `AuthServer` CR should look like this:
  
    ```yaml
    kind: AuthServer
    metadata:
      annotations:
        "sso.apps.tanzu.vmware.com/allow-unsafe-cors": ""
    spec:
      cors:
        allowAllOrigins: true
    ```
2. **Discover your `AppSSO` service offerings**
   ```bash
   tanzu services classes list
   ```

   If there isn't one,

  - ask your service operator `OR`
  - [create your own `ClusterUnsafeTestLogin`](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.6/tap/app-sso-reference-api-clusterunsafetestlogin.html)
3. **Select the service offering you'd like to connect** -- claim an instance from the offering

      ```bash
       tanzu services class-claims create angular-frontend-claim \
       --namespace "<your-workload-namespace>" \
       --class "<your-selected-appsso-offering>" \
       --parameter workloadRef.name="<your-workload-name>" \
       --parameter redirectPaths='["/", "/user-profile", "/customer-profiles/list"]' \
       --parameter authorizationGrantTypes='["authorization_code"]' \
       --parameter clientAuthenticationMethod='none' \
       --parameter scopes='[{name: "openid"}]'
      ```
4. **Apply your workload**
   ```bash
   kubectl apply -f config
   ```
5. **Retrieve your client identifier (`clientId`)**
   ```shell
   kubectl get secret --namespace "<your-workload-namespace>" -o jsonpath='{.data.client-id}' \
      $(kubectl get classclaim -n "<your-workload-namespace>" angular-frontend-claim -o jsonpath='{.status.binding.name}') \
      | base64 -d
   ```
1. **Retrieve your Issuer URI**
   ```shell
   kubectl get authserver --namespace "<authserver-namespace>" \
      -o jsonpath='{.status.issuerURI}' \
      <authserver-name>
   ```
1. **Configure Angular app** - your Angular app should have an `src/assets/auth.conf.json` file that needs configuration. The items in the file correspond to the following:
    ```json
    {
      "clientId": "<your-client-id-from-previous-step>",
      "authority": "<issuer-uri-from-previous-step>",
      "scope": [
        "openid" // This list MUST contain `openid`
      ]
    }
    ```
    The `scopes` listed here are default scopes. Replace with the scopes appropriate for your application. Any scopes you want to have available here also need to be listed when claiming an AppSSO service offering
8. **Navigate to the Workload URL when ready** - you should be redirected to AppSSO authorization server and prompted to authenticate.

## Using User Data Returned from AppSSO
To learn how to use user data returned from an access token, you can check out the user-profile module.
