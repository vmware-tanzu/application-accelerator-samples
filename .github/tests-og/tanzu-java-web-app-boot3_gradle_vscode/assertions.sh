#!/bin/bash
set -euxo pipefail

pushd $1
# Check that native README is not included
c=$(grep -c 'GraalVM' README.md || true)
if [ $c -gt 0 ]; then
  echo "GraalVM should not be in README.md"
  exit 1
fi  

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
if ! grep -q -F '/bin/main' "Tiltfile"; then
  echo "FAIL: Tiltfile isn't suitable for gradle vscode project?"
  echo "Couldn't find the string 'bin/main' in it."
  exit 1
fi
./gradlew build
popd