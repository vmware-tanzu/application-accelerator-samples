#!/bin/sh
source ./testFunctions.sh

TESTNAME='pgVectorAzureOpenAI'
PROJNAME='pgVectorAzureOpenAI'

echo "=== ${TESTNAME}:"

tanzu accelerator generate --server-url http://localhost:8877 --options-file ./${TESTNAME}.json spring-ai-chat 1>/dev/null
unzip -d $TESTNAME ${PROJNAME}.zip 1>/dev/null
assertFileExists ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.azure.openai.api-key=myapikey'
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.azure.openai.model=gpt-4'
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.vectorstore.pgvector.index-type=ivfflat'
assertFileContains ./$TESTNAME/${PROJNAME}/src/main/resources/application.properties 'spring.ai.vectorstore.pgvector.distance-type=euclidean_distance'

assertFileExists ./$TESTNAME/${PROJNAME}/pom.xml
assertPomHasProjectCoordinates ./$TESTNAME/${PROJNAME}/pom.xml 'com.example' 'pgVectorAzureOpenAI'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-azure-openai-spring-boot-starter' '${SPRING_AI_VERSION}'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-tika-document-reader' '${SPRING_AI_VERSION}'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-pgvector-store' '${SPRING_AI_VERSION}'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.boot' 'spring-boot-starter-jdbc'
assertPomHasDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.postgresql' 'postgresql'
assertPomDoesntHaveDependency ./$TESTNAME/${PROJNAME}/pom.xml 'org.springframework.ai' 'spring-ai-openai-spring-boot-starter' '${SPRING_AI_VERSION}'

assertFileExists ./$TESTNAME/${PROJNAME}/config/workload.yaml
assertFileContains ./$TESTNAME/${PROJNAME}/config/workload.yaml 'serviceClaims'
assertFileContains ./$TESTNAME/${PROJNAME}/config/workload.yaml 'name: vector-store'

assertFileDoesntExist ./$TESTNAME/${PROJNAME}/src/main/java/com/example/aichat/SimpleVectorStoreConfig.java

rm -rf $TESTNAME
rm ${PROJNAME}.zip
