#!/bin/bash
set -euxo pipefail

pushd $1
if ls | grep -q gradle; then
   echo "FAIL: There are gradle related files present."
   exit 1
fi

./mvnw package
popd