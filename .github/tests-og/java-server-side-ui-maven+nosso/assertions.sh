#!/bin/bash
set -euxo pipefail
pushd $1

# Check that pom.xml exists
test -f pom.xml

# Check that build.gradle does not exist
test ! -f build.gradle

# Check that live-update files exist
test -f Tiltfile
test -f DEPLOYING.md

./mvnw package

popd