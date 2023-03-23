#!/bin/bash
set -euxo pipefail
pushd $1

./mvnw package

popd