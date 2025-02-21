#!/bin/bash
set -euxo pipefail
pushd $1

# Check that correct TP files exist
test -f .tanzu/config/streaming-app.yml
test -f .tanzu/config/service-binding-foo-broker.yml
test -f .tanzu/config/rabbitmq-instance-foo-broker.yml
test -f tanzu.yml

# Check the source and model files exist
test -f src/main/java/com/example/stream/model/Foo.java
test -f src/main/java/com/example/stream/functions/FooSource.java

# Check the processor and sink classes files don't exist
test ! -f src/main/java/com/example/stream/functions/*Processor.java
test ! -f src/main/java/com/example/stream/functions/*Sink.java

./mvnw package

popd