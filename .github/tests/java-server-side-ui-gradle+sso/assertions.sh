#!/bin/bash
set -euxo pipefail
pushd $1

# Check that build.gradle.kts exists
test -f build.gradle.kts

# Check that pom.xml does not exist
test ! -f pom.xml

# Check that OIDC files exist
test -f src/main/java/com/example/serversideuistarter/web/OidcUserController.java

./gradlew build

popd