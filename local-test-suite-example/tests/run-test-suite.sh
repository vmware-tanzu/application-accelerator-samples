#!/bin/bash
set -euo pipefail

cd $(dirname ${BASH_SOURCE[0]})

./run-engine.sh &
echo $! > engine_pid
engine_pid=$(cat engine_pid)
java_pid=$(ps -ef | grep 'com.vmware.tanzu.accelerator.local.server.MainKt' | grep $engine_pid | tr -s ' ' | cut -d ' ' -f3)
echo $java_pid > engine_pid
echo "Engine PID: $java_pid"

while ! nc -z localhost 8378; do sleep 3; done

BASE=$PWD
PATTERN=*
export WORKSPACE=$PWD/workspace
mkdir -p $WORKSPACE

echo "START $(date)" > $WORKSPACE/results.txt
echo "" >> $WORKSPACE/results.txt

pushd suite
for test_name in ${PATTERN}; do
  echo "**--------------------------------"
  echo "** TESTING: $test_name"
  echo "**--------------------------------"
  echo "TESTING: $test_name" >> $WORKSPACE/results.txt
  $BASE/acc-generate.sh $test_name
  $BASE/acc-run-test.sh $test_name
  echo "COMPLETE: $test_name" >> $WORKSPACE/results.txt
  echo "" >> $WORKSPACE/results.txt
done
popd

echo "** SUCCESS"
echo "SUCCESS $(date)" >> $WORKSPACE/results.txt

echo "Stopping process $java_pid"
kill $java_pid

echo "--------- TEST REPORT ------------------------------------"
cat $WORKSPACE/results.txt
echo "----------------------------------------------------------"
