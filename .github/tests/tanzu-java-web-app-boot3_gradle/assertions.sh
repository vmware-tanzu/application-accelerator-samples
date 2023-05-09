#!/bin/bash
set -euxo pipefail

pushd $1
if [ -f pom.xml ]; then
  echo "Maven pom.xml shouldn't exist"
  exit 1
fi
if [ -e mvnw ] || [ -e mvnw.cmd ] || [ -e .mvn ]; then
  echo "Maven wrapper files shouldn't exist"
  exit 1
fi
if [ -e Tiltfile_gradle ]; then
  echo "Tiltfile_gradle shouldn't exist (should be renamed to Tiltfile)"
  exit 1
fi
if ! grep -q -F '/build/classes/java/main' "Tiltfile"; then
  echo "Tiltfile isn't suitable for gradle project?"
  echo "Couldn't find build/classes/java/main in it."
  exit 1
fi
./gradlew build
popd