#!/bin/sh
source ./testFunctions.sh

TESTNAME='projectcoords'
PROJNAME='coordstest'

echo "=== ${TESTNAME}:"

tanzu accelerator generate --server-url http://localhost:8877 --options-file ./${TESTNAME}.json spring-ai-chat 1>/dev/null
unzip -d $TESTNAME ${PROJNAME}.zip 1>/dev/null

assertDirDoesntExist ./$TESTNAME/${PROJNAME}/accelerator-tests

assertFileExists ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.openai.api-key=${OPENAI_API_KEY}'
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.openai.model=gpt-3.5-turbo'

assertFileExists ./$TESTNAME/${PROJNAME}/pom.xml
assertPomHasProjectCoordinates ./$TESTNAME/${PROJNAME}/pom.xml 'com.test' 'testcoords'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.ai' 'spring-ai-pgvector-store' '${SPRING_AI_VERSION}'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomDoesntHaveDependency ./$TESTNAME/spring-ai-chat/pom.xml 'org.postgresql' 'postgresql'

assertFileExists ./$TESTNAME/${PROJNAME}/config/workload.yaml
assertFileDoesntContain ./$TESTNAME/${PROJNAME}/config/workload.yaml 'serviceClaims'
assertFileDoesntContain ./$TESTNAME/${PROJNAME}/config/workload.yaml 'name: vector-store'

assertFileExists ./$TESTNAME/${PROJNAME}/src/main/java/com/example/aichat/SimpleVectorStoreConfig.java


rm -rf $TESTNAME
rm ${PROJNAME}.zip
