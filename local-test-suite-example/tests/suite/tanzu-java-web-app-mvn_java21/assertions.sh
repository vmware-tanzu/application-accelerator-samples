#!/bin/bash
set -euxo pipefail

source $(dirname ${BASH_SOURCE[0]})/../../testFunctions.sh

pushd $1

assertFileExists ./pom.xml
assertFileDoesntExist ./build.gradle.kts
assertFileContains ./pom.xml '<java.version>21</java.version>'

./mvnw package

popd
