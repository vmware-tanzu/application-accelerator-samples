#!/bin/bash
set -euxo pipefail
pushd $1

# Check the sink and model files exist
test -f src/main/java/com/example/stream/model/Baz.java
test -f src/main/java/com/example/stream/functions/BazSink.java

# Check the source and processor classes files don't exist
test ! -f src/main/java/com/example/stream/functions/*Source.java
test ! -f src/main/java/com/example/stream/functions/*Processor.java

./mvnw package

popd