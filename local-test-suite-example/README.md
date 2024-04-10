# Local Accelerator Authoring Test Suite Example

This directory contains an example of a test suite you can create for testing accelerators that you are authoring.

## Setup

To be able to run this example test suite you need to clone the application-accelerator-samples repository:

```sh
git clone https://github.com/vmware-tanzu/application-accelerator-samples.git
cd application-accelerator-samples
```

Next, create an `accelerators` directory in the cloned repo:

```sh
mkdir accelerators
```

Finally, copy the `spring-cloud-serverless` accelerator to the `accelerators` directory so we can serve it up with the local Application Accelerator engine server:

```sh
cp -r spring-cloud-serverless accelerators/spring-cloud-serverless
```

To run the local engine server you can follow instructions from the ["Install the local engine server"](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/1.9/tap/application-accelerator-creating-accelerators-using-local-engine-server.html#install-the-local-engine-server-1) section in the documentation.

Before starting the local engine server you need to point it to the location where you checked out the `application-accelerator-samples` repository. If you are still at the root of the checkout clone then you can find the path by running:

```sh
echo $PWD
```

Then export this location in a new terminal used for starting the local engine server:

> NOTE: Modify the directory in the first `cd` command to match where the downloaded engine server is located on your system and also the `export` of the location for the `application-accelerator-samples` repo.

```sh
cd ~/Downloads/acc-engine
export ACC_LOCAL_FILES=~/workspace/vmware-tanzu/application-accelerator-samples
./engine
```

## Creating the test suite

The scripts included in this example relies on a directory structure where each test in the test suite is in a separate directory named based on a `test-suite-name`-`suffix` pattern. This example uses:

```
.
├── spring-cloud-serverless-k8s
└── spring-cloud-serverless-tap
```

where `spring-cloud-serverless` is the test suite name and each test has a descriptive suffix.

Each test contains two files:

```
.
└── spring-cloud-serverless-tap
    ├── assertions.sh
    └── options.json
```

Where `options.json` file is used to specify the configurable options for generating the accelerator and `assertions.sh` is a script the will be run to check the generated content.

The `options.json` file can be created using the [Tanzu App Accelerator VS Code extension](https://marketplace.visualstudio.com/items?itemName=vmware.tanzu-app-accelerator). There is an "Export Options" button for the "Review and Generate" step. You can use this to export the option values selected in the "Configure Accelerator" step.

Here is an example of the `options.json` file (reformatted for readability):

```json
{
  "projectName" : "spring-cloud-serverless",
  "javaVersion" : "17",
  "deploymentType" : "workload",
  "includeBuildToolWrapper" : true
}
```

The `assertions.sh` script can then be used to verify the generated content. Here is an example based on the above options:

```sh
#!/bin/bash
set -euxo pipefail
source ../../.github/tests/testFunctions.sh

pushd $1

# Verify TAP files
assertFileExists ./src/main/resources/application.properties
assertFileExists ./Tiltfile
assertFileExists ./.tanzuignore
assertFileExists ./config/workload.yaml
assertFileContains ./config/workload.yaml 'value: "17"'

# Check that Kubernetes files don't exist
assertFileDoesntExist ./kubernetes/deployment.yaml
assertFileDoesntExist ./kubernetes/service.yaml

# Verify the pom.xml file
assertFileExists ./pom.xml
assertFileContains ./pom.xml '<java.version>17</java.version>'
assertPomHasProjectCoordinates ./pom.xml 'com.example' 'spring-cloud-serverless'
assertPomHasDependency ./pom.xml 'org.springframework.cloud' 'spring-cloud-function-web'

./mvnw --quiet --no-transfer-progress verify

popd
```

A few things to mention here:

1. We source a `../../.github/tests/testFunctions.sh` file available from the samples [repo](https://github.com/vmware-tanzu/application-accelerator-samples/blob/main/.github/tests/testFunctions.sh).
1. The script expects to get the directory for the test as first argument so we can switch to that directory
1. We can provide as many asserts that we think is reasonable to test the accelerator generated content
1. We can also provide a build command to verify the project can build and run tests

## Running the test suite

This example has three scripts that are used to drive the test suite runs.

1. `run-test-suite.sh` is the top level script that orchestrates the run
1. `generate.sh` generates the accelerator using the options from each tests `options.json` file
1. `run-test.sh` runs the `assertion.sh` script against the generated content

The accelerator content is generated into a local `/tmp/results` directory.

Any assertion failures should result in a message displayed with the actual failure:

```
+ assertFileContains ./config/workload.yaml 'value: "21"'
+ grep -Fq 'value: "21"' ./config/workload.yaml
+ echo './config/workload.yaml DOES NOT contain "value: "21""'
./config/workload.yaml DOES NOT contain "value: "21""
+ exit 1
```
When you have resolved any issues the test suite should finish with a success message:

```
**--------------------------------
** TESTING: spring-cloud-serverless-k8s
**--------------------------------

...

** COMPLETE: spring-cloud-serverless-k8s

**--------------------------------
** TESTING: spring-cloud-serverless-tap
**--------------------------------

...

** COMPLETE: spring-cloud-serverless-tap

** SUCCESS
```
