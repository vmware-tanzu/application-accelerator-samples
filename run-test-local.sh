#!/bin/bash
set -euo pipefail

export GITHUB_WORKSPACE=$(pwd)

test_name=tanzu-java-web-app-boot2
#test_name=tanzu-java-web-app-boot3

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

.github/tests/generate.sh "$test_name"
.github/tests/run-test.sh "$test_name"
