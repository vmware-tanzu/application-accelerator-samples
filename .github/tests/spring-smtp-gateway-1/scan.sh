#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

./mvnw package
$grype smtp-gateway/target/smtp-gateway-0.0.1-SNAPSHOT.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype smtp-sink/target/smtp-sink-0.0.1-SNAPSHOT.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd