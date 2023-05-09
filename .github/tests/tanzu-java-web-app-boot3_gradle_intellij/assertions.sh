#!/bin/bash
set -euo pipefail

pushd $1
if [ -f pom.xml ]; then
  echo "FAIL: Maven pom.xml shouldn't exist"
  exit 1
fi
if [ -e mvnw ] || [ -e mvnw.cmd ] || [ -e .mvn ]; then
  echo "FAIL: Maven wrapper files shouldn't exist"
  exit 1
fi
if ls | grep -q "Tilfile_gradle*"; then
  echo "FAIL: Tiltfile_gradle* shouldn't exist (should be renamed to Tiltfile)"
  exit 1
fi
if ! grep -q -F '/build/classes/java/main' "Tiltfile"; then
  echo "FAIL: Tiltfile isn't suitable for gradle intellij project?"
  echo "Couldn't find the string 'build/classes/java/main' in it."
  exit 1
fi
./gradlew build
popd