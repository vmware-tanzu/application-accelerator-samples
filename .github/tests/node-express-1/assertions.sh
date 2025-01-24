#!/bin/bash
set -euxo pipefail
pushd $1

# Check that TP files exist
test -f .tanzu/config/node-express.yml
test -f tanzu.yml

npm install

popd