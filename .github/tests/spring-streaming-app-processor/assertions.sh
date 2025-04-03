#!/bin/bash
set -euxo pipefail
pushd $1

# Check the processor and model files exist
test -f src/main/java/com/example/stream/model/Bar.java
test -f src/main/java/com/example/stream/functions/BarProcessor.java

# Check the source and sink classes files don't exist
test ! -f src/main/java/com/example/stream/functions/*Source.java
test ! -f src/main/java/com/example/stream/functions/*Sink.java

./mvnw package

popd