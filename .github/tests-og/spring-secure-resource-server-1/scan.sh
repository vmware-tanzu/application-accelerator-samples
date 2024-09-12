#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

./mvnw package
$grype audit/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype postal-code-search/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd