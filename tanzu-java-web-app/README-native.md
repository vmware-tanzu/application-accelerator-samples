
## Deploying the sample on TAP built as a GraalVM native image

You can build and deploy the `config/workload-native.yaml` file using the Apply Workload option for Tanzu Developer Tools in your IDE.


You can also use the Tanzu CLI to build and deploy this sample as a GraalVM native image.
With the Tanzu CLI, you can use the following command:

```sh
tanzu apps workload create tanzu-java-web-app \
  --file ./config/workload.yaml \
  --local-path . \
  --build-env "BP_NATIVE_IMAGE=true" \
  --build-env "BP_MAVEN_BUILD_ARGUMENTS=-Pnative -Dmaven.test.skip=true --no-transfer-progress package -Dspring-boot.aot.jvmArguments='-Dmanagement.health.probes.enabled='true' -Dmanagement.endpoint.health.probes.add-additional-paths='true' -Dmanagement.endpoint.health.show-details='always' -Dmanagement.endpoints.web.base-path='/actuator' -Dmanagement.endpoints.web.exposure.include='*' -Dmanagement.server.port=8081 -Dserver.port=8080'" \
  --env MANAGEMENT_HEALTH_PROBES_ENABLED=true \
  --env MANAGEMENT_ENDPOINT_HEALTH_PROBES_ADD_ADDITIONAL_PATHS=true  \
  --env MANAGEMENT_ENDPOINT_HEALTH_SHOW_DETAILS=always \
  --env MANAGEMENT_ENDPOINTS_WEB_BASE_PATH="/actuator" \
  --env MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE="*"  \
  --env MANAGEMENT_SERVER_PORT=8081 \
  --env SERVER_PORT=8080
```
