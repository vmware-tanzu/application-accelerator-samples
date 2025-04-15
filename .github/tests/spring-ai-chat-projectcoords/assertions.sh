#!/bin/bash
set -euxo pipefail
source ../testFunctions.sh

pushd $1

assertFileExists ./src/main/resources/application.properties
assertFileContains ./src/main/resources/application.properties 'spring.ai.openai.api-key=${AI_API_KEY}'
assertFileContains ./src/main/resources/application.properties 'spring.ai.openai.model=gpt-3.5-turbo'

assertFileExists ./pom.xml
assertPomHasProjectCoordinates ./pom.xml 'com.test' 'testcoords'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-starter-model-openai' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.ai' 'spring-ai-starter-vector-store-pgvector' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomDoesntHaveDependency ./pom.xml 'org.postgresql' 'postgresql'

assertFileExists ./src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

popd
