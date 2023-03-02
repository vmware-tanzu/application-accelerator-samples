## Authorization
If you want to use the Tanzu AppSSO Authserver, there are a few things to consider

### Inner loop (development mode)
#### Configuring the `auth.conf.json`
In your Angular app, there should be an `auth.conf.json`. The items in there refer to the following:
```json
{
  "clientId": "your-client-id",
  "authority": "https://your-authorization-server.com/",
  "redirectUri": "http://localhost:4200/",
  "scope": [
    "openid" // This list MUST contain openid
  ]
}
```

#### Configuring the Authserver
To enable a single page app with an AppSSO Authserver you will need `CORS`. For this, the `Authserver CR` has an `AuthServer.spec.cors` spec. Your
`Authserver CR` `CORS` section should look like the following:

```yaml
kind: AuthServer
spec:
  cors:
    allowOrigins:
      - http://127.0.0.1:4200/
```

Alternatively, if you want to configure the AuthServer to allow all origins, the `Authserver CR` should look like this:

```yaml
kind: AuthServer
metadata:
  annotations:
    - "sso.apps.tanzu.vmware.com/allow-unsafe-cors": ""
spec:
  cors:
    allowAllOrigins: true
```

AppSSO does not allow `localhost` redirect URIs. For local testing, you should start your app with `npm run serve`, as this command
is defined to use the flag `--host 127.0.0.1`. The `AuthServer` will need to recognize your client, so you want to define your `ClientRegistration` CR as follows:

```yaml
apiVersion: sso.apps.tanzu.vmware.com/v1alpha1
kind: ClientRegistration
metadata:
  name: <your-app-name>
#  namespace: #@ data.values.samples.namespace
#  annotations:
#    kapp.k14s.io/change-rule: "upsert after upserting authservers"
spec:
  authServerSelector:
    matchLabels:
      name: <your-authserver-name>
  authorizationGrantTypes:
    - authorization_code
  clientAuthenticationMethod: none
#  requireUserConsent: false
  redirectURIs:
    - http://127.0.0.1:4200/
    - http://127.0.0.1:4200/customer-profiles/list
    - http://127.0.0.1:4200/user-profile
  scopes:
    - name: openid
    - name: email
    - name: profile
```

__Note:__ The `clientAuthenticationMethod` has to be set to `none` in order for a single page application to work with an AppSSO AuthServer.

### Using the returned user data
Check out the user-profile module to see how user data from an access_token can be used.
