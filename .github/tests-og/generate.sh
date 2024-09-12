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
echo SERVER_URL=$SERVER_URL

${CMD} ${PLUGIN} generate-from-local \
  --server-url $SERVER_URL \
  --output-dir /tmp/result \
  --fragment-paths                         devcontainer=fragments/devcontainer \
  --fragment-paths                       app-sso-client=fragments/app-sso-client \
  --fragment-paths                         java-version=fragments/java-version \
  --fragment-paths                          live-update=fragments/live-update \
  --fragment-paths                         java-version=fragments/java-version \
  --fragment-paths   spring-boot-app-sso-auth-code-flow=fragments/spring-boot-app-sso-auth-code-flow \
  --fragment-paths                 spring-boot-database=fragments/spring-boot-database \
  --fragment-paths                       spring-boot-h2=fragments/spring-boot-h2 \
  --fragment-paths                    spring-boot-mysql=fragments/spring-boot-mysql \
  --fragment-paths               spring-boot-postgresql=fragments/spring-boot-postgresql \
  --fragment-paths                  steeltoe-postgresql=fragments/steeltoe-postgresql \
  --fragment-paths                 testcontainers-mysql=fragments/testcontainers-mysql \
  --fragment-paths            testcontainers-postgresql=fragments/testcontainers-postgresql \
  --fragment-paths                       tap-initialize=fragments/tap-initialize \
  --fragment-paths                         tap-workload=fragments/tap-workload \
  --fragment-paths                  build-wrapper-maven=fragments/build-wrapper-maven \
  --fragment-paths                 build-wrapper-gradle=fragments/build-wrapper-gradle \
  --accelerator-path ${accname}=${accname} \
  $([ -f .github/tests/$1/options.json ] && echo --options-file .github/tests/$1/options.json)

popd
