# Authorization
To leverage the benefits of Tanzu AppSSO Authserver, there are a few things you should take into consideration.

## Inner Loop (Development Mode)
### Configuration of auth.conf.json
Your Angular app should have an `src/assets/auth.conf.json` file that needs configuration. The items in the file correspond to the following:

```json
{
  "clientId": "your-client-id",
  "authority": "https://your-authorization-server.com/",
  "scope": [
    "openid" // This list MUST contain openid
  ]
}
```

__Note:__ The scpoes listed here are default scopes. Replace with the scopes appropriate for your application. Any scopes you 
want to have available here also need to be listed in the `clientregistration.yaml`

## Authserver Configuration
To enable a single page app with an AppSSO `AuthServer`, you will need to define allowed HTTP origins, as part of CORS. You can specify CORS settings in the `AuthServer` CR `spec.cors` section. Here are some examples:

```yaml
kind: AuthServer
spec:
  cors:
    allowOrigins:
      - http://127.0.0.1:4200/
```

__Note:__ Replace the redirect uri with your application's uri

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

Note that localhost redirect URIs are not allowed with AppSSO. For local testing, it is recommended to use `npm run serve`, which includes the `--host 127.0.0.1` flag. The `AuthServer` should recognize your client, so it is necessary to define your `ClientRegistration` CR as follows:

The following `clientregistration.yaml` should be in your `config` directory.

```yaml
apiVersion: sso.apps.tanzu.vmware.com/v1alpha1
kind: ClientRegistration
metadata:
  name: <your-app-name>
spec:
  authServerSelector:
    matchLabels:
      name: <your-authserver-name>
  authorizationGrantTypes:
    - authorization_code
  clientAuthenticationMethod: none
  redirectURIs:
    - http://127.0.0.1:4200/
    - http://127.0.0.1:4200/customer-profiles/list
    - http://127.0.0.1:4200/user-profile
  scopes:
    - name: openid
    - name: email
    - name: profile
    - name: message.read
    - name: message.write
```

__Note__ that the `clientAuthenticationMethod` must be set to none for a single page application to work with an AppSSO `AuthServer`.

__Note:__ The scopes listed here are default scopes. Replace with the scopes appropriate for your application.

## Using User Data Returned from AppSSO
To learn how to use user data returned from an access token, you can check out the user-profile module.
