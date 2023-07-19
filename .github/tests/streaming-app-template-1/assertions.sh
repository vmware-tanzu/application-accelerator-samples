#!/bin/bash
set -euxo pipefail
pushd $1

# Check that TAP files exist
test -f config/workload.yaml
test -f Tiltfile
test -f catalog/catalog-info.yaml

# Check the Source and model files exist
test -f src/main/java/com/example/tanzu/streamtemplate/model/Foo.java
test -f src/main/java/com/example/tanzu/streamtemplate/functions/FooSource.java

# Check the processor and sink classes files don't exist
test ! -f src/main/java/com/example/tanzu/streamtemplate/functions/FooProcessor.java
test ! -f src/main/java/com/example/tanzu/streamtemplate/functions/FooSink.java

./mvnw package

popd