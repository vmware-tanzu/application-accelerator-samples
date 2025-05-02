#!/bin/bash
set -euo pipefail

source $(dirname ${BASH_SOURCE[0]})/../../testFunctions.sh

pushd $1

assertFileExists ./pom.xml
assertFileDoesntExist ./build.gradle.kts
assertFileContains ./pom.xml '<java.version>17</java.version>'

./mvnw package

popd
