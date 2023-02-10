#!/bin/bash
set -euxo pipefail
pushd $1

# Check that build.gradle.kts exists
test -f build.gradle.kts

# Check that pom.xml does not exist
test ! -f pom.xml

# Check that build.gradle contains mysql and not postgres driver
grep -q 'mysql-connector-j' build.gradle.kts
! grep -q 'postgresql' build.gradle.kts

# Check that build.gradle contains flyway and not liquibase
grep -q 'flyway' build.gradle.kts
! grep -q 'liquibase' build.gradle.kts

./gradlew build

popd