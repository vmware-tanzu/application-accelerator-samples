#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

./mvnw package

$grype where-for-dinner-ui --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml
echo "TODO: Scan Java services when the following Grype issue (which produces many false positives) is resolved https://github.com/anchore/grype/issues/1009"

popd