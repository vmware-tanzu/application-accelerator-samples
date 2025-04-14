#!/bin/bash
set -euxo pipefail
source ../testFunctions.sh

pushd $1

assertFileExists ./src/main/resources/application.properties
assertFileContains ./src/main/resources/application.properties 'spring.ai.azure.openai.api-key=${AI_API_KEY}'
assertFileContains ./src/main/resources/application.properties 'spring.ai.azure.openai.model=gpt-4'
assertFileContains ./src/main/resources/application.properties 'spring.ai.vectorstore.pgvector.index-type=ivfflat'
assertFileContains ./src/main/resources/application.properties 'spring.ai.vectorstore.pgvector.distance-type=euclidean_distance'

assertFileExists ./pom.xml
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'pgVectorAzureOpenAI'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-starter-model-azure-openai' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-starter-vector-store-pgvector' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomHasDependency ./pom.xml 'org.postgresql' 'postgresql'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.ai' 'spring-ai-starter-model-openai' '${SPRING_AI_VERSION}'

assertFileDoesntExist ./src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

./mvnw verify -DskipTests

popd
