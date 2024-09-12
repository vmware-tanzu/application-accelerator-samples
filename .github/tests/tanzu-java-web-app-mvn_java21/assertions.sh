#!/bin/bash
set -euxo pipefail

pushd $1

if ls | grep -q gradle; then
   echo "FAIL: There are gradle related files present."
   exit 1
fi

if ! grep -q '<java.version>21</java.version>' pom.xml; then
   echo "Couldn't find Java version 21"
   exit 2
fi

./mvnw package

popd