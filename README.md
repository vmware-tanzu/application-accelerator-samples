# application-accelerator-samples

> **NOTE:** the Application Accelerator samples for Tanzu Application Platform are available in the [tap-og](https://github.com/vmware-tanzu/application-accelerator-samples/tree/tap-og) branch.

## Overview

Application Accelerators for VMware Tanzu helps you bootstrap developing your applications and deploying them in a discoverable and repeatable way.

Enterprise Architects author and publish accelerator projects that provide developers and operators in their organization ready-made, enterprise-conformant code and configurations.

Published accelerator projects are maintained in Git repositories. You can then use Application Accelerators to create new projects based on those accelerator projects.

The Application Accelerators user interface(UI) enables you to discover available accelerators, configure them, and generate new projects to download.

## How does Application Accelerators work?

Application Accelerators allow you to generate new projects from files in Git repositories. An accelerator.yaml file in the repository declares input options for the accelerator. This file also contains instructions for processing the files when you generate a new project.

Accelerator custom resources (CRs) control which repositories appear in the Application Accelerators UI. You can maintain CRs by using Kubernetes tools such as kubectl or by using the Tanzu CLI accelerator commands. The Accelerator controller reconciles the CRs with a Flux2 Source Controller to fetch files from GitHub or GitLab.

The Application Accelerators UI and IDE extension gives you a searchable list of accelerators to choose from. After you select an accelerator, the UI and IDE extension present input fields for any input options.

Application Accelerators UI and IDE extension send the input values to the Accelerator Engine for processing. The Engine then returns the project in a ZIP file. You can open the project in your favorite integrated development environment (IDE) to develop further.

### Prerequisites

To use these Application Accelerators samples you need to have access to an installation of [VMware Tanzu Application Platform](https://network.tanzu.vmware.com/products/tanzu-application-platform).

### Build & Run

Each Accelerator is provided in a separate directory and is its own self contained project with a README file descibing how to build it.

### Testing

The test suite runs in GitHub actions om each push.

You can run the tests locally by setting the following environment variables:

```sh
export TANZU_CLI=tanzu
export CLI_PLUGIN=acc
export TEST_PATTERN=tanzu-java-web-app-*  # set this to a pattern that matches the tests you want to run
export TEST_WORKSPACE=$PWD
export ACC_SERVER_URL=<URL>  # this is the URL for Tanzu Portal (TAP-GUI) in your view cluster
```

Then run the tests using:

```sh
./run-test-local.sh
```

## Documentation

_Application Accelerators for VMware Tanzu_ documentation is provided as part of the official documentation for [VMware Tanzu Application Platform](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/index.html)

## Contributing

The application-accelerator-samples project team welcomes contributions from the community. Before you start working with application-accelerator-samples, please
read our [Developer Certificate of Origin](https://cla.vmware.com/dco). All contributions to this repository must be
signed as described on that page. Your signature certifies that you wrote the patch or have the right to pass it on
as an open-source patch. For more detailed information, refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## License

[MIT No Attribution](https://opensource.org/licenses/MIT-0) (MIT-0) License
