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
  echo "Maven pom.xml shouldn't exist"
  exit 1
fi
if [ -e mvnw ] || [ -e mvnw.cmd ] || [ -e .mvn ]; then
  echo "Maven wrapper files shouldn't exist"
  exit 1
fi
./gradlew build
popd