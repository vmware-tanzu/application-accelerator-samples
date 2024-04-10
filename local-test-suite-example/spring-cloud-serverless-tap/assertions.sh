#!/bin/bash
set -euxo pipefail
source ../../.github/tests/testFunctions.sh

pushd $1

# Verify TAP files
assertFileExists ./src/main/resources/application.properties
assertFileExists ./Tiltfile
assertFileExists ./.tanzuignore
assertFileExists ./config/workload.yaml
assertFileContains ./config/workload.yaml 'value: "17"'

# Check that Kubernetes files don't exist
assertFileDoesntExist ./kubernetes/deployment.yaml
assertFileDoesntExist ./kubernetes/service.yaml

# Verify the pom.xml file
assertFileExists ./pom.xml
assertFileContains ./pom.xml '<java.version>17</java.version>'
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'spring-cloud-serverless'
assertPomHasDependency ./pom.xml 'org.springframework.cloud' 'spring-cloud-function-web'

./mvnw --quiet --no-transfer-progress verify

popd