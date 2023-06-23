#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

./mvnw package

$grype where-for-dinner-ui --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-crawler-python --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
echo "Scan Java services - depends on exclusions in .grype.yaml"
$grype where-for-dinner-api-gateway/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-availability/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-crawler/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-notify/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-notify/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-search/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
$grype where-for-dinner-search-proc/target/*.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd