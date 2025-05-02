#!/bin/bash
set -euo pipefail

testdir=$1
[[ $1 =~ (.*)-.* ]]
accname=${BASH_REMATCH[1]}

assertions=$PWD/$testdir/assertions.sh

echo "VERIFYING: $testdir" >> $WORKSPACE/results.txt
echo "ASSERTIONS: $assertions" >> $WORKSPACE/results.txt

pushd ${WORKSPACE}/$testdir/$accname

[ -f $assertions ] && $assertions .

popd
