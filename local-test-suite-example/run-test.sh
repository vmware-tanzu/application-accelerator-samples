#!/bin/bash
set -euo pipefail

[[ $1 =~ (.*)-.* ]]
accname=${BASH_REMATCH[1]}

pushd ${WORKSPACE}/$1

[ -f assertions.sh ] && ./assertions.sh /tmp/result/$accname

popd
