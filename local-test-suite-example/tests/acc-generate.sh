#!/bin/bash
set -euo pipefail

testdir=$1
[[ $1 =~ (.*)-.* ]]
accname=${BASH_REMATCH[1]}

echo Test: $testdir
echo Options: $testdir/options.json

echo "GENERATING: $accname" >> $WORKSPACE/results.txt

options=$(cat $testdir/options.json)

echo "OPTIONS:" >> $WORKSPACE/results.txt
echo "$options" >> $WORKSPACE/results.txt

mkdir -p ${WORKSPACE}/$testdir
rm -rf ${WORKSPACE}/$testdir/*

pushd ${WORKSPACE}/$testdir

curl http://localhost:8378/api/accelerators/zip?name=$accname -H 'Content-Type: application/json' -d "{\"options\":$options}" -o $accname.zip
unzip $accname.zip

popd
