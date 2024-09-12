#!/bin/bash
set -euxo pipefail
pushd $1

# Check that build.gradle.kts exists
test -f build.gradle.kts

# Check that pom.xml does not exist
test ! -f pom.xml

# Check that build.gradle contains postgres and not mysql driver
grep -q 'postgresql' build.gradle.kts
! grep -q 'mysql-connector-j' build.gradle.kts

# Check that build.gradle contains liquibase and not flyway
grep -q 'liquibase' build.gradle.kts
! grep -q 'flyway' build.gradle.kts

./gradlew build -x test

popd
