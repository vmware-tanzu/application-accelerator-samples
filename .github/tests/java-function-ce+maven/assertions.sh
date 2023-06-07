#!/bin/bash
set -euxo pipefail
pushd $1

# Check that pom.xml exists
test -f pom.xml

# Check that build.gradle does not exist
test ! -f build.gradle

# Check that TAP files exist
test -f config/workload.yaml
test -f catalog/catalog-info.yaml
test -f Tiltfile

./mvnw package

popd