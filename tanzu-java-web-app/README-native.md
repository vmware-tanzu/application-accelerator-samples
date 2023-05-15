
## Deploying the sample on TAP built as a GraalVM native image

In order to build this sample as a GraalVM native image, you can use the following command:

```sh
tanzu apps workload create tanzu-java-web-app \
  --file ./config/workload.yaml \
  --local-path . \
  --source-image "${SOURCE_IMAGE}" \
  --build-env "BP_NATIVE_IMAGE=true" \
  --build-env "BP_MAVEN_BUILD_ARGUMENTS=-Pnative -Dmaven.test.skip=true --no-transfer-progress package -Dspring-boot.aot.jvmArguments='-Dmanagement.health.probes.enabled='true' -Dmanagement.endpoint.health.probes.add-additional-paths='true' -Dmanagement.server.port=8081 -Dserver.port=8080' " \
  --env MANAGEMENT_HEALTH_PROBES_ENABLED=true \
  --env MANAGEMENT_ENDPOINT_HEALTH_PROBES_ADD_ADDITIONAL_PATHS=true  \
  --env MANAGEMENT_SERVER_PORT=8081 \
  --env SERVER_PORT=8080
```
