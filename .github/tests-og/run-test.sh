#!/bin/bash
set -euo pipefail

[[ $1 =~ (.*)-.* ]]

WORKSPACE="${TEST_WORKSPACE:-$GITHUB_WORKSPACE}"
pushd ${WORKSPACE}/.github/tests/$1

[ -f assertions.sh ] && ./assertions.sh /tmp/result

popd
