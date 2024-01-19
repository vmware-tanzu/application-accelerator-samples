#!/bin/bash
set -euxo pipefail
pushd $1

# Check that pom.xml exists
test -f pom.xml

# Check that build.gradle does not exist
test ! -f build.gradle

# Check that pom.xml contains mysql and not postgres driver
grep -q 'mysql-connector-j' pom.xml
! grep -q 'postgresql' pom.xml

# Check that pom.xml contains liquibase and not flyway
grep -q 'liquibase' pom.xml
! grep -q 'flyway' pom.xml

./mvnw package -DskipTests

popd