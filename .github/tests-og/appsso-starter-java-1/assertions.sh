#!/bin/bash
set -euxo pipefail

for f in $( (cd expected && find * ) ) ; do
  diff expected/$f $1/$f
done

pushd $1
./gradlew build
popd
