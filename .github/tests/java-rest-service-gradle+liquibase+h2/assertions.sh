#!/bin/bash
set -euxo pipefail
pushd $1

# Check that build.gradle.kts exists
test -f build.gradle.kts

# Check that pom.xml does not exist
test ! -f pom.xml

# Check that build.gradle contains postgres and h2 driver
grep -q 'postgresql' build.gradle.kts
grep -q 'com.h2database:h2' build.gradle.kts

# Check that build.gradle contains liquibase and not flyway
grep -q 'liquibase' build.gradle.kts
! grep -q 'flyway' build.gradle.kts

./gradlew build

popd
