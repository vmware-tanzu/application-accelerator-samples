# Accelerator Log

## Options
```json
{
  "artifactId" : "testcoords",
  "groupId" : "com.test",
  "projectName" : "coordstest"
}
```
## Log
```
┏ engine (Chain)
┃  Info Running Chain(GeneratorValidationTransform, UniquePath)
┃ ┏ ┏ engine.transformations[0].validated (Combo)
┃ ┃ ┃  Info Combo running as Let
┃ ┃ ┃ engine.transformations[0].validated.delegate (Let)
┃ ┃ ┃ Debug Adding symbol packageDirectory with value 'com/example/aichat'
┃ ┃ ┃ Debug Adding symbol workloadResourceName with value 'testcoords'
┃ ┃ ┃ Debug Adding symbol empty with value ''
┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in (Chain)
┃ ┃ ┃ ┃  Info Running Chain(Combo, Combo, Combo, Combo, Combo, Provenance)
┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[0] (Combo)
┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'simple') evaluated to false
┃ ┃ ┃ ┃ ┗ null ()
┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1] (Combo)
┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate (Chain)
┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(Combo, Combo, Combo, Combo, Combo, Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[0] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Exclude
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[0].delegate (Exclude)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will exclude [accelerator-tests/**]
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh matched [accelerator-tests/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [accelerator-tests/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[1].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, Exclude)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[1].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [**]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[1].delegate.transformations[1] (Exclude)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will exclude [pom.xml, README.md]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml matched [pom.xml, README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md matched [pom.xml, README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [pom.xml, README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText, ReplaceText, ReplaceText, ReplaceText, ReplaceText, ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [pom.xml]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml matched [pom.xml] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [pom.xml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace [<groupId>com.vmware.tap.accelerators</groupId>-><groupId>com.test</g...(truncated)]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[2] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace [<artifactId>ai-chat</artifactId>-><artifactId>testcoor...(truncated)]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[3] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'pgvector') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace regex '<dependency>\s*<groupId>org.springframework.ai</groupId>\s*<artifactId>spring-ai-pgvector-store</artifactId>\s*<version>\$\{SPRING_AI_VERSION\}</version>\s*</dependency>' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[4] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'pgvector') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace regex '<dependency>\s*<groupId>org.springframework.boot</groupId>\s*<artifactId>spring-boot-starter-jdbc</artifactId>\s*</dependency>' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[5] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'pgvector') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace regex '<dependency>\s*<groupId>org.postgresql</groupId>\s*<artifactId>postgresql</artifactId>\s*<scope>runtime</scope>\s*</dependency>' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[2].delegate.transformations[6] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#aiService == 'azureOpenAI') evaluated to false
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ null ()
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[3] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[3].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText, ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[3].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [README.md]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md matched [README.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [README.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[3].delegate.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace regex '--- StartGradle[\s\S]+?--- EndGradle' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[3].delegate.transformations[2] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗  Info Will replace regex '--- StartMaven\s|--- EndMaven\s' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[4] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[4].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[4].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [config/workload.yaml]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml matched [config/workload.yaml] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [config/workload.yaml] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[4].delegate.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'pgvector') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗  Info Will replace regex '\s\sserviceClaims\:\n\s*\-\s*name\:\s*vector-store\n\s*\s*ref\:\n\s*\s*\s*apiVersion\:\s*services.apps.tanzu.vmware.com/v1alpha1\n\s*\s*\s*kind\:\s*ResourceClaim\n\s*\s*\s*name\:\s*vector-store' with ''
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, Combo, Combo, Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [src/main/resources/application.properties]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties matched [src/main/resources/application.properties] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [src/main/resources/application.properties] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#aiService == 'openAI') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[1].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[1].delegate.transformations[0] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗  Info Will replace [spring.ai.openai.model=gpt-3.5-turbo->spring.ai.openai.mod...(truncated), spring.ai.openai.api-key=${OPENAI_API_KEY}->spring.ai.openai.api...(truncated)]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[2] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#aiService == 'azureOpenAI') evaluated to false
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ null ()
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[3] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#vectorStoreType != 'pgvector') evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[3].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[0].sources[5].delegate.transformations[3].delegate.transformations[0] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┗ ┗ ┗ ┗  Info Will replace [spring.ai.vectorstore.pgvector.index-type=ivfflat->, spring.ai.vectorstore.pgvector.distance-type=euclidean_distance->]
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[1].delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'docker-compose.yaml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/ai-chat-0.0.1-SNAPSHOT.jar', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/encodings.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/Question.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/misc.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/templates/login.html', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/memory/Memory.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/application.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/operator/DefaultAiOperator.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'tap-values.yaml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/workspace.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/static/actions.js', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/memory/Memory.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/operator/AiOperator.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/jarRepositories.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/templates/home.html', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/UploadController.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/ChatController.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw.cmd', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/.gitignore', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/templates/home.html', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.mvn/wrapper/maven-wrapper.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'README.md', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/Answer.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/static/User.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/static/User.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/static/style.css', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/static/System.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/operator/AiOperator$Builder.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/compiler.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/ai-chat-0.0.1-SNAPSHOT.jar.original', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/operator/DefaultAiOperator.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/application.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.gitignore', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/static/style.css', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.mvn/wrapper/maven-wrapper.jar', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'pom.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/org/springframework/ai/memory/ConversationBufferMemory.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/static/AI.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/static/AI.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/vcs.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/resources/templates/login.html', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/maven-archiver/pom.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/static/actions.js', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/Answer.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.idea/spring-ai-chat.iml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/org/springframework/ai/operator/AiOperator.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/Question.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'config/workload.yaml', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'target/classes/static/System.png', will use the one appearing last 
┃ ┃ ┃ ┃ ┗ ┗ Debug Multiple representations for path 'src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java', will use the one appearing last 
┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2] (Combo)
┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[2].delegate (Chain)
┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(InvokeFragment, Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0] (InvokeFragment)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(Combo, Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[0] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#liveUpdateIDESupport) evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[0].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[0].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [Tiltfile]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile matched [Tiltfile] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug README.md didn't match [Tiltfile] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[0].delegate.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗  Info Will replace [my-project->testcoords]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#liveUpdateIDESupport) evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[1].delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[1].delegate.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [DEPLOYING.md]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md matched [DEPLOYING.md] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug README.md didn't match [DEPLOYING.md] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[0].sources[1].delegate.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ ┗  Info Will replace [my-project->testcoords]
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ ╺ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[0].validated.delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Include
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[0].sources[1].delegate (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [**]
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┗ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java matched [**] -> included
┃ ┃ ┃ ┃ ┗ ╺ engine.transformations[0].validated.delegate.in.transformations[2].delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3] (Combo)
┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[3].delegate (Chain)
┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(Combo, Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[0] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Exclude
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[0].delegate (Exclude)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will exclude [**/*.java]
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java matched [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Let
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1].delegate (Let)
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Adding symbol packageDirectory with value 'com/example/aichat'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1].delegate.in (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Include, ReplaceText, RewritePath)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1].delegate.in.transformations[0] (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [**/*.java]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Question.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/Answer.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [**/*.java] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java matched [**/*.java] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1].delegate.in.transformations[1] (ReplaceText)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗  Info Will replace [com.vmware.tap.accelerators.aichat->com.example.aichat]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[0].sources[1].delegate.in.transformations[2] (RewritePath)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/Question.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/Question.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/Question.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/Question.java} and was rewritten to 'src/main/java/com/example/aichat//Question.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/ChatServiceApplication.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/ChatServiceApplication.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/ChatServiceApplication.java} and was rewritten to 'src/main/java/com/example/aichat//ChatServiceApplication.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/SecurityConfig.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/SecurityConfig.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/SecurityConfig.java} and was rewritten to 'src/main/java/com/example/aichat//SecurityConfig.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/AIConfig.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/AIConfig.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/AIConfig.java} and was rewritten to 'src/main/java/com/example/aichat//AIConfig.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=test, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/ChatServiceApplicationTests.java, g0=src/test/java/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.java, g1=test, g2=com/vmware/tap/accelerators/, g3=/ChatServiceApplicationTests.java} and was rewritten to 'src/test/java/com/example/aichat//ChatServiceApplicationTests.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/WebConfig.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/WebConfig.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/WebConfig.java} and was rewritten to 'src/main/java/com/example/aichat//WebConfig.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/SimpleVectorStoreConfig.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/SimpleVectorStoreConfig.java} and was rewritten to 'src/main/java/com/example/aichat//SimpleVectorStoreConfig.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/Answer.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/Answer.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/Answer.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/Answer.java} and was rewritten to 'src/main/java/com/example/aichat//Answer.java'
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/ChatController.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/ChatController.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/ChatController.java} and was rewritten to 'src/main/java/com/example/aichat//ChatController.java'
┃ ┃ ┃ ┃ ┃ ┗ ┗ ┗ ┗ Debug Path 'src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java' matched 'src/(?<sourceset>.*)/java/(?<currentpackage>.*/)aichat(?<untouchedpath>.*)' with groups {sourceset=main, currentpackage=com/vmware/tap/accelerators/, untouchedpath=/UploadController.java, g0=src/main/java/com/vmware/tap/accelerators/aichat/UploadController.java, g1=main, g2=com/vmware/tap/accelerators/, g3=/UploadController.java} and was rewritten to 'src/main/java/com/example/aichat//UploadController.java'
┃ ┃ ┃ ┃ ┗ ╺ engine.transformations[0].validated.delegate.in.transformations[3].delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4] (Combo)
┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[4].delegate (Chain)
┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(InvokeFragment, Combo, InvokeFragment)
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[0] (InvokeFragment)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[0].validated (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#bsGitRepository != null) evaluated to false
┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ null ()
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[1] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Include
┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[1].delegate (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [**]
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/Question.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/AIConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/example/aichat/ChatServiceApplicationTests.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/ChatController.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/Answer.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/SimpleVectorStoreConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/SecurityConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/WebConfig.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/ChatServiceApplication.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┗ Debug src/main/java/com/example/aichat/UploadController.java matched [**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2] (InvokeFragment)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Chain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated.delegate (Chain)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Chain(Merge, UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated.delegate.transformations[0] (Merge)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Running Merge(Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated.delegate.transformations[0].sources[0] (Combo)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Condition (#includeBuildToolWrapper) evaluated to true
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Combo running as Include
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated.delegate.transformations[0].sources[0].delegate (Include)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃  Info Will include [mvnw, mvnw.cmd, .mvn/**]
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug docker-compose.yaml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/encodings.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.txt didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/misc.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/login.html didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/Memory.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/application.properties didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperator.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultAiOperatorBuilder.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/Question.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/createdFiles.lst didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug tap-values.yaml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/workspace.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/runAllTests.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SecurityConfig.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/actions.js didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/Memory.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.json didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/compile/default-compile/inputFiles.lst didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/jarRepositories.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/AIConfig.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/defaults.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/test/java/com/example/aichat/ChatServiceApplicationTests.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/home.html didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/ChatController.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatController.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/.gitignore didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/templates/home.html didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Answer.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/User.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/memory/ConversationBufferMemory.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/User.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/AIConfig.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitOpenAi.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/style.css didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/Answer.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/SimpleVectorStoreConfig.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/System.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/AiOperator$Builder.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/compiler.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/ai-chat-0.0.1-SNAPSHOT.jar.original didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperator.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/application.properties didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/operator/DefaultPromptTemplateStrings.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .gitignore didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultAiOperatorBuilder.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/SecurityConfig.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/style.css didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.jar matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug pom.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/test-classes/com/vmware/tap/accelerators/aichat/ChatServiceApplicationTests.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/org/springframework/ai/memory/ConversationBufferMemory.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/WebConfig.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/AI.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/static/AI.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/vcs.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/createdFiles.lst didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/DefaultPromptTemplateStrings.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.json didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.json didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/resources/templates/login.html didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-archiver/pom.properties didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/explicitAzureOpenAi.json didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/actions.js didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorAzureOpenAI.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/projectcoords.json didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/ChatServiceApplication.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .idea/spring-ai-chat.iml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/maven-status/maven-compiler-plugin/testCompile/default-testCompile/inputFiles.lst didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/org/springframework/ai/operator/AiOperator.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/UploadController$UploadResponse.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/ChatServiceApplication.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/Question.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/pgVectorOpenAI.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/surefire-reports/TEST-com.vmware.tap.accelerators.aichat.ChatServiceApplicationTests.xml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/SimpleVectorStoreConfig.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/com/vmware/tap/accelerators/aichat/WebConfig.class didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug config/workload.yaml didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Tiltfile didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug DEPLOYING.md didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug target/classes/static/System.png didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug accelerator-tests/testFunctions.sh didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug src/main/java/com/example/aichat/UploadController.java didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug mvnw.cmd matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug README.md didn't match [mvnw, mvnw.cmd, .mvn/**] -> excluded
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug .mvn/wrapper/maven-wrapper.properties matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ┗ Debug mvnw matched [mvnw, mvnw.cmd, .mvn/**] -> included
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[0].sources[2].validated.delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw.cmd', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┗ ┗ ┗ ┗ Debug Multiple representations for path '.mvn/wrapper/maven-wrapper.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┏ engine.transformations[0].validated.delegate.in.transformations[4].delegate.transformations[1] (UniquePath)
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path 'mvnw.cmd', will use the one appearing last 
┃ ┃ ┃ ┃ ┃ ┃ Debug Multiple representations for path '.mvn/wrapper/maven-wrapper.properties', will use the one appearing last 
┃ ┃ ┃ ┃ ┗ ┗ Debug Multiple representations for path '.mvn/wrapper/maven-wrapper.jar', will use the one appearing last 
┃ ┗ ┗ ┗ ╺ engine.transformations[0].validated.delegate.in.transformations[5] (Provenance)
┗ ╺ engine.transformations[1] (UniquePath)
```
