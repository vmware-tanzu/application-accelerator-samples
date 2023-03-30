#!/bin/bash
set -euo pipefail

[[ $1 =~ (.*)-.* ]]
grype_location=$2

pushd ${GITHUB_WORKSPACE}/.github/tests/$1

[ -f scan.sh ] && ./scan.sh /tmp/result $grype_location

popd
