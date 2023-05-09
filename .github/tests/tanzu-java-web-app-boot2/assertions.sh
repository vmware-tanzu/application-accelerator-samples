#!/bin/bash
set -euxo pipefail

pushd $1
if [ -f build.gradle.kts -o -f settings.gradle ]; then
  echo "Gradle files shouldn't exist"
  exit 1
fi
if [ -f build.gradle.kts -o -f settings.gradle ]; then
  echo "Gradle files shouldn't exist"
  exit 1
fi
if [ -e gradle -o -e gradlew -o -e gradlew.bat ]; then
  echo "Gradle wrapper files shouldn't exist"
  exit 1
fi
./mvnw package
popd