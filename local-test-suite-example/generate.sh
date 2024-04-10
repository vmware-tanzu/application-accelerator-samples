#!/bin/bash
set -euo pipefail

rm -fr /tmp/result
mkdir /tmp/result

testdir=$1
[[ $1 =~ (.*)-.* ]]
accname=${BASH_REMATCH[1]}

echo WORKSPACE=$WORKSPACE
pushd ${WORKSPACE}

CMD="${TANZU_CLI:-.github/tests/tanzu-accelerator-linux_amd64}"
PLUGIN="${CLI_PLUGIN:-}"

${CMD} ${PLUGIN} generate $accname \
    --local-server \
    --output-dir /tmp/result/$accname \
    --extract \
    $([ -f $testdir/options.json ] && echo --options-file $testdir/options.json)

popd
