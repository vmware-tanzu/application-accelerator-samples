#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

dotnet build
$grype dir:. --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd