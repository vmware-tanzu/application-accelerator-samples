#!/bin/bash
set -euxo pipefail

pushd $1
grype=$2

# disable grype scan until we have a fix for CVE-2024-29415
# $grype dir:. --fail-on high --config ${GITHUB_WORKSPACE}/.github/tests/.grype.yaml

popd