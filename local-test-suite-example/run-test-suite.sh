#!/bin/bash
set -euo pipefail

# Setup for local testing
# 
export TANZU_CLI=tanzu
export CLI_PLUGIN=acc
export PATTERN=spring-cloud-serverless-*  # set this to a pattern that matches the tests you want to run
export WORKSPACE=$PWD

for test_name in ${PATTERN}; do
  echo "**--------------------------------"
  echo "** TESTING: $test_name"
  echo "**--------------------------------"
  ./generate.sh $test_name
  ./run-test.sh $test_name
  echo "** COMPLETE: $test_name"
  echo ""
done

echo "** SUCCESS"
