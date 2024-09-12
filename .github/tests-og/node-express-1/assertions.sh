#!/bin/bash
set -euxo pipefail
pushd $1

# Check that TAP files exist
test -f config/workload.yaml
test -f catalog/catalog-info.yaml

npm install

popd