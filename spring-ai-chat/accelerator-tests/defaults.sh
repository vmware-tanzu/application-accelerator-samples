#!/bin/sh
source ./testFunctions.sh

TESTNAME='defaults'

echo "=== ${TESTNAME}:"

tanzu accelerator generate --server-url http://localhost:8877 spring-ai-chat 1>/dev/null
unzip -d $TESTNAME spring-ai-chat.zip 1>/dev/null

assertDirDoesntExist ./$TESTNAME/spring-ai-chat/accelerator-tests

assertFileExists ./$TESTNAME/spring-ai-chat/src/main/resources/application.properties
assertFileContains ./$TESTNAME/spring-ai-chat/src/main/resources/application.properties 'spring.ai.openai.api-key=${OPENAI_API_KEY}'
assertFileContains ./$TESTNAME/spring-ai-chat/src/main/resources/application.properties 'spring.ai.openai.model=gpt-3.5-turbo'

assertFileExists ./$TESTNAME/spring-ai-chat/pom.xml
assertPomHasProjectCoordinates ./$TESTNAME/spring-ai-chat/pom.xml 'com.example' 'spring-ai-chat' '0.0.1-SNAPSHOT' 'spring-ai-chat'
assertPomHasDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.ai' 'spring-ai-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomHasDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.ai' 'spring-ai-pgvector-store' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.postgresql' 'postgresql'

assertFileExists ./$TESTNAME/spring-ai-chat/config/workload.yaml
assertFileDoesntContain ./$TESTNAME/spring-ai-chat/config/workload.yaml 'serviceClaims'
assertFileDoesntContain ./$TESTNAME/spring-ai-chat/config/workload.yaml 'name: vector-store'

assertFileExists ./$TESTNAME/spring-ai-chat/src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

rm -rf $TESTNAME
rm spring-ai-chat.zip
