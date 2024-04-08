#!/bin/bash
set -euxo pipefail
pushd $1

# Check that TAP files exist
test -f audit/config/workload.yaml
test -f audit/Tiltfile
test -f postal-code-search/config/workload.yaml
test -f postal-code-search/Tiltfile
test -f catalog/catalog-info.yaml

# Check that workloads path is rewritten
test -f config/developer/workloads.yaml

# Check that workloads path is rewritten
test -f config/developer/workloads.yaml

# Check that service operator config exists
test -f config/service-operator/appSSOInstance.yaml
test -f config/service-operator/scgInstance.yaml

# Check that app operator config exists
test -f config/app-operator/ingress.yaml
test -f config/app-operator/rmqClassClaim.yaml
test -f config/app-operator/scgRoutes.yaml
test -f config/app-operator/workloadClientRegistration.yaml

./mvnw package

popd