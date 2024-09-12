#!/bin/bash
set -euxo pipefail
pushd $1

# Check that workloads path is rewritten
test -f config/developer/workloads.yaml

# Check that RabbitMQ files exist
test -f config/service-operator/rmqCluster.yaml

# Check that MySQL files don't exist
test ! -f config/service-operator/mysqlInstance.yaml
test ! -f config/app-operator/mysqlResourceClaim.yaml

# Check that cloudevents files don't exist
test ! -f config/app-operator/knEventing.yaml

# Check that security files don't exist
test ! -f config/service-operator/appSSOInstance.yaml

# Check that redis files don't exist
test ! -f config/service-operator/redisInstance.yaml
test ! -f config/app-operator/redisResourceClaim.yaml

./mvnw package

popd