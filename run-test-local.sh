#!/bin/bash
set -euo pipefail

export GITHUB_WORKSPACE=$(pwd)

docker pull dev.registry.tanzu.vmware.com/app-accelerator/acc-engine:latest
acc_server=$(docker run -d -p 8888:8080 dev.registry.tanzu.vmware.com/app-accelerator/acc-engine:latest)
finish() {
    docker stop $acc_server
}
trap finish EXIT ERR
until $(curl --output /dev/null --silent --head --fail http://localhost:8888/actuator/health); do
  printf 'Waiting for acc server...'
  sleep 2
done

pushd .github/tests
for test_name in tanzu-java-*; do
   ./generate.sh $test_name
   ./run-test.sh $test_name
done
popd