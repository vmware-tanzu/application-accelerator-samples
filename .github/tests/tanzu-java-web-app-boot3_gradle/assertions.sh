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
./gradlew build
popd