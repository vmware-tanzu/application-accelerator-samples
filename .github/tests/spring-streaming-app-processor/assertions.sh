#!/bin/bash
set -euxo pipefail
pushd $1

# Check that correct TP files exist
test -f .tanzu/config/streaming-app.yml
test -f .tanzu/config/service-binding-bar-broker.yml
test ! -f .tanzu/config/rabbitmq-instance-bar-broker.yml
test -f tanzu.yml

# Check the processor and model files exist
test -f src/main/java/com/example/stream/model/Bar.java
test -f src/main/java/com/example/stream/functions/BarProcessor.java

# Check the source and sink classes files don't exist
test ! -f src/main/java/com/example/stream/functions/*Source.java
test ! -f src/main/java/com/example/stream/functions/*Sink.java

./mvnw package

popd