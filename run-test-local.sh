#!/bin/bash
set -euo pipefail

# Setup for local testing
# 
# export TANZU_CLI=tanzu
# export CLI_PLUGIN=acc
# export TEST_PATTERN=tanzu-java-web-app-*  # set this to a pattern that matches the tests you want to run
# export TEST_WORKSPACE=$PWD
# export TEST_WORKSPACE=$PWD
# export ACC_SERVER_URL=<URL>  # this is the URL for Tanzu Portal (TAP-GUI) in your view cluster

PATTERN="${TEST_PATTERN:-*}"

pushd .github/tests
for test_name in ${PATTERN}; do
  echo "** TESTING $test_name **"
  ./generate.sh $test_name
  ./run-test.sh $test_name
done
popd
