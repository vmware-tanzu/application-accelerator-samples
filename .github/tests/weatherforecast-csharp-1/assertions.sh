#!/bin/bash
set -euxo pipefail
pushd $1

# Check that applicationName is used
test -f NewApp.csproj

popd