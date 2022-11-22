#!/bin/bash
set -euxo pipefail
pushd $1

# Check that pom.xml exists
test -f pom.xml

# Check that build.gradle does not exist
test ! -f build.gradle

# Check that pom.xml contains flyway and not liquibase
grep -q 'flyway' pom.xml
! grep -q 'liquibase' pom.xml

./mvnw package

popd