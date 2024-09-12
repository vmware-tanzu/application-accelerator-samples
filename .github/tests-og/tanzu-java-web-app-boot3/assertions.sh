#!/bin/bash
set -euxo pipefail

pushd $1
# Check that native README is not included
c=$(grep -c 'GraalVM' README.md || true)
if [ $c -gt 0 ]; then
  echo "GraalVM should not be in README.md"
  exit 1
fi  

if ls | grep -q gradle; then
   echo "FAIL: There are gradle related files present."
   exit 1
fi
./mvnw package
popd