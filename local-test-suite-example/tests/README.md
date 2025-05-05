# Local Accelerator Authoring Test Suite Example

This directory contains an example of a test suite you can create for testing accelerators that you are authoring.

## Setup

To be able to run this example test suite you need to download the Accelerator Local Server Engine ZIP archive and extract it.

You also need to download the `local-test-suite-example.zip` file and extract it inside he directory where the Accelerator Local Server Engine ZIP file was extracted.

## Creating a test suite

The scripts included in this example relies on a directory structure where each test in the test suite is in a separate directory named based on a `test-suite-name`-`suffix` pattern. This example uses:

```
tests/suite
├── tanzu-java-web-app-defaults
│   ├── assertions.sh
│   └── options.json
└── tanzu-java-web-app-mvn_java21
    ├── assertions.sh
    └── options.json
```

where `tanzu-java-web-app` is the test suite name and each test has a descriptive suffix like `-defaults` and `mvn_java21` in this example.

Each test contains two files:

```
.
└── tanzu-java-web-app-mvn_java21
    ├── assertions.sh
    └── options.json
```

Where `options.json` file is used to specify the configurable options for generating the accelerator and `assertions.sh` is a script the will be run to check the generated content.

The `options.json` file can be created using the [Tanzu App Accelerator VS Code extension](https://marketplace.visualstudio.com/items?itemName=vmware.tanzu-app-accelerator). There is an "Export Options" button for the "Review and Generate" step. You can use this to export the option values selected in the "Configure Accelerator" step.

Here is an example of the `options.json` file:

```json
{
  "projectName": "tanzu-java-web-app",
  "buildTool": "maven",
  "javaVersion": "21",
  "includeBuildToolWrapper": true
}
```

The `assertions.sh` script can then be used to verify the generated content. Here is an example based on the above options:

```sh
#!/bin/bash
set -euxo pipefail

source $(dirname ${BASH_SOURCE[0]})/../../testFunctions.sh

pushd $1

assertFileExists ./pom.xml
assertFileDoesntExist ./build.gradle.kts
assertFileContains ./pom.xml '<java.version>21</java.version>'

./mvnw package

popd
```

A few things to mention here:

1. We source a `../../.github/tests/testFunctions.sh` file available in the `local-test-suite-example` download.
1. The script expects to get the directory for the test as first argument so we can switch to that directory
1. We can provide as many asserts that we think is reasonable to test the accelerator generated content
1. We can also provide a build command to verify the project can build and run tests

## Running the test suite

We need to change to the directory with the test suite scripts which should be inside the directory where you extracted the Accelerator Local Server Engine ZIP file. with the :

```sh
cd <local-server-engine-directory>
cd tests
```

Then, we simply run the `run-test-suite.sh` script:

```sh
./run-test-suite.sh
```

This example has three scripts that are used to drive the test suite runs.

1. `run-test-suite.sh` is the top level script that orchestrates the run
1. `run-engine.sh` starts a local engine server for generating the accelerators in the test.
1. `acc-generate.sh` generates the accelerator using the options from each tests `options.json` file
1. `acc-run-test.sh` runs the `assertion.sh` script against the generated content

The accelerator content is generated into a local `workspace` directory and a `workspace/results.txt` file is created containing a test report.

Any assertion failures should result in a message displayed with the actual failure:

```
+ assertFileContains ./config/workload.yaml 'value: "21"'
+ grep -Fq 'value: "21"' ./config/workload.yaml
+ echo './config/workload.yaml DOES NOT contain "value: "21""'
./config/workload.yaml DOES NOT contain "value: "21""
+ exit 1
```
When you have resolved any issues the test suite should finish with a success message and the test report:

```
** SUCCESS
Stopping process 81692
--------- TEST REPORT ------------------------------------
START Fri May  2 15:38:49 EDT 2025

TESTING: tanzu-java-web-app-defaults
GENERATING: tanzu-java-web-app
OPTIONS:
{
  "projectName": "tanzu-java-web-app"
}
VERIFYING: tanzu-java-web-app-defaults
ASSERTIONS: /Users/risbergt/test/acc-engine/tests/suite/tanzu-java-web-app-defaults/assertions.sh
COMPLETE: tanzu-java-web-app-defaults

TESTING: tanzu-java-web-app-mvn_java21
GENERATING: tanzu-java-web-app
OPTIONS:
{
  "projectName": "tanzu-java-web-app",
  "buildTool": "maven",
  "javaVersion": "21",
  "includeBuildToolWrapper": true
}
VERIFYING: tanzu-java-web-app-mvn_java21
ASSERTIONS: /Users/risbergt/test/acc-engine/tests/suite/tanzu-java-web-app-mvn_java21/assertions.sh
COMPLETE: tanzu-java-web-app-mvn_java21

SUCCESS Fri May  2 15:39:08 EDT 2025
----------------------------------------------------------
```
