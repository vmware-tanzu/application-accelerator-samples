#!/bin/bash
set -euxo pipefail
source ../../.github/tests/testFunctions.sh

pushd $1

# Check that K8s files exist
assertFileExists ./kubernetes/deployment.yaml
assertFileExists ./kubernetes/service.yaml
assertFileExists ./Tiltfile
assertFileExists ./.tanzuignore

# Check that TAP workload doesn't exist
assertFileDoesntExist ./config/workload.yaml

# Verify the pom.xml file
assertFileExists ./pom.xml
assertFileContains ./pom.xml '<java.version>21</java.version>'
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'spring-cloud-serverless'
assertPomHasDependency ./pom.xml 'org.springframework.cloud' 'spring-cloud-function-web'

./mvnw --quiet --no-transfer-progress verify

popd