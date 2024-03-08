#!/bin/bash
set -euxo pipefail
source ../testFunctions.sh

pushd $1

assertFileExists ./src/main/resources/application.properties
assertFileContains ./src/main/resources/application.properties 'spring.ai.azure.openai.api-key=myapikey'
assertFileContains ./src/main/resources/application.properties 'spring.ai.azure.openai.model=gpt-4'
assertFileContains ./src/main/resources/application.properties 'spring.ai.azure.openai.endpoint=https://azureendpoint'

assertFileExists ./pom.xml
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'explicitAzureOpenAi'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-azure-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomHasDependency ./pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.ai' 'spring-ai-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.ai' 'spring-ai-pgvector-store' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomDoesntHaveDependency ./pom.xml 'org.postgresql' 'postgresql'

assertFileExists ./config/workload.yaml
assertFileDoesntContain ./config/workload.yaml 'serviceClaims'
assertFileDoesntContain ./config/workload.yaml 'name: vector-store'

assertFileExists ./src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

./mvnw verify

popd
