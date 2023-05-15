#!/bin/bash
set -euxo pipefail

pushd $1
# Check that native README is not included
c=$(grep -c 'GraalVM' README.md || true)
if [ $c -gt 0 ]; then
  echo "GraalVM should not be in README.md"
  exit 1
fi  

if [ -f build.gradle.kts ] || [ -f settings.gradle ]; then
  echo "Gradle files shouldn't exist"
  exit 1
fi
if [ -e gradle ] || [ -e gradlew ] || [ -e gradlew.bat ]; then
  echo "Gradle wrapper files shouldn't exist"
  exit 1
fi
./mvnw package
popd