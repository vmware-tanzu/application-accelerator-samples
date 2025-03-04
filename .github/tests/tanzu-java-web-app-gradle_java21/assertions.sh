#!/bin/bash
set -euo pipefail

pushd $1

if [ -f pom.xml ]; then
   echo "FAIL: Found pom.xml file!"
   exit 1
fi

if ! grep -q 'JavaVersion.VERSION_21' build.gradle.kts; then
   echo "Couldn't find Java version 21"
   exit 2
fi

./gradlew build

popd