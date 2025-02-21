#!/bin/bash
set -euxo pipefail
pushd $1

# Check that correct TP files exist
test -f .tanzu/config/streaming-app.yml
test -f .tanzu/config/service-binding-baz-broker.yml
test ! -f .tanzu/config/rabbitmq-instance-baz-broker.yml
test -f tanzu.yml

# Check the sink and model files exist
test -f src/main/java/com/example/stream/model/Baz.java
test -f src/main/java/com/example/stream/functions/BazSink.java

# Check the source and processor classes files don't exist
test ! -f src/main/java/com/example/stream/functions/*Source.java
test ! -f src/main/java/com/example/stream/functions/*Processor.java

./mvnw package

popd