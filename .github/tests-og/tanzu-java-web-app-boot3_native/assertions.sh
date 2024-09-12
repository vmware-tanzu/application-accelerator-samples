#!/bin/bash
set -euxo pipefail

pushd $1
if ls | grep -q gradle; then
   echo "FAIL: There are gradle related files present."
   exit 1
fi

# Check that native README is included
grep -q 'GraalVM' README.md

./mvnw package
popd