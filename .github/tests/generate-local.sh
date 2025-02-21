#!/bin/bash
set -euo pipefail

rm -fr /tmp/result
mkdir /tmp/result

[[ $1 =~ (.*)-.* ]]
accname=${BASH_REMATCH[1]}

WORKSPACE="${TEST_WORKSPACE:-$GITHUB_WORKSPACE}"

echo WORKSPACE=$WORKSPACE
pushd ${WORKSPACE}

CMD="${TANZU_CLI:-.github/tests/tanzu-accelerator-linux_amd64}"
PLUGIN="${CLI_PLUGIN:-}"

SERVER_URL="${ACC_SERVER_URL:-http://localhost:8888}"

echo TEST=$1
echo SERVER_URL=$SERVER_URL
echo ACCELERATOR=${accname}

${CMD} ${PLUGIN} generate ${accname} \
  --server-url $SERVER_URL \
  --output-dir /tmp/result \
    --extract \
  $([ -f .github/tests/$1/options.json ] && echo --options-file .github/tests/$1/options.json)

popd
