#!/bin/bash
set -euxo pipefail
source ../testFunctions.sh

pushd $1

assertFileExists ./src/main/resources/application.properties
assertFileContains ./src/main/resources/application.properties 'spring.ai.openai.api-key=${AI_API_KEY}'
assertFileContains ./src/main/resources/application.properties 'spring.ai.openai.model=gpt-3.5-turbo'

assertFileExists ./pom.xml
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'spring-ai-chat' '0.0.1-SNAPSHOT' 'spring-ai-chat'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.ai' 'spring-ai-pgvector-store' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomDoesntHaveDependency ./pom.xml 'org.postgresql' 'postgresql'

assertFileExists ./config/workload.yaml
assertFileDoesntContain ./config/workload.yaml 'serviceClaims'
assertFileDoesntContain ./config/workload.yaml 'name: vector-store'

assertFileExists ./src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

./mvnw verify

popd
