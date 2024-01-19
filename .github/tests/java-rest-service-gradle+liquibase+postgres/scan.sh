#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

./gradlew build -x test
$grype build/libs/customer-profile-0.0.1-SNAPSHOT.jar --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd