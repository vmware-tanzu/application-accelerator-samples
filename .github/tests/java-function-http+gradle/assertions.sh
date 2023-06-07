#!/bin/bash
set -euxo pipefail
pushd $1

# Check that build.gradle exists
test -f build.gradle

# Check that pom.xml does not exist
test ! -f pom.xml

# Check that TAP files exist
test -f config/workload.yaml
test -f catalog/catalog-info.yaml
test -f Tiltfile

./gradlew build

popd