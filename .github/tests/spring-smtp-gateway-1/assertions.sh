#!/bin/bash
set -euxo pipefail
pushd $1

# Check that RabbitMQ files exist
test -f config/service-operator/rmqCluster.yaml
test -f config/app-operator/rmqResourceClaim.yaml

./mvnw package

popd