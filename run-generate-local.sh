#!/bin/bash
set -euo pipefail

# Setup for local testing
# 
# export TANZU_CLI=tanzu
# export CLI_PLUGIN=acc
# export TEST_PATTERN=tanzu-java-web-app-*  # set this to a pattern that matches the tests you want to run
# export TEST_WORKSPACE=$PWD
# export ACC_SERVER_URL=<URL>  # this is the URL for Tanzu Portal (TAP-GUI) in your view cluster

PATTERN="${TEST_PATTERN:-*}"

pushd .github/tests
for test_name in ${PATTERN}; do
  if [[ "$test_name" == *".sh"* || "$test_name" == "tanzu-accelerator-linux_amd64" ]]; then
    echo "** skipping $test_name **"
  else
    echo "** GENERATING $test_name **"
    ./generate.sh $test_name
  fi
done
popd
