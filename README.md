# application-accelerator-samples

> **NOTE:** the Application Accelerator samples for Tanzu Application Platform are available in the [tap-og](https://github.com/vmware-tanzu/application-accelerator-samples/tree/tap-og) branch.

## Overview

Application Accelerators for VMware Tanzu helps you bootstrap developing your applications and deploying them in a discoverable and repeatable way.

Enterprise Architects author and publish accelerator projects that provide developers and operators in their organization ready-made, enterprise-conformant code and configurations.

Published accelerator projects are maintained in Git repositories. You can then use Application Accelerators to create new projects based on those accelerator projects.

The Application Accelerators user interface(UI) enables you to discover available accelerators, configure them, and generate new projects to download.

## How does Application Accelerators work?

Application Accelerators allow you to generate new projects from files in Git repositories. An `accelerator.yaml` file in the repository declares input options for the accelerator. An `accelerator.axl` file contains instructions for processing the files when you generate a new project.

A metadata Git repository and/or a local metadata file can provide metadata for accelerator resources that should be available in the UI of the Application Accelerators IDE plugin.

The Application Accelerators IDE plugin gives you a searchable list of accelerators to choose from. After you select an accelerator, the IDE plugin present input fields for any input options.

Application Accelerators IDE plugins send the input values to the Accelerator Engine for processing. The Engine then returns the project in a ZIP file. You can open the project in your favorite integrated development environment (IDE) to develop further.

### Prerequisites

To use these Application Accelerators samples you need to have access to an installation of [VMware Tanzu Platform](https://www.vmware.com/products/app-platform/tanzu).

### Build & Run

Each Accelerator is provided in a separate directory and is its own self contained project with a README file descibing how to build it.

### Testing

There is a [local example test suite](local-test-suite-example/README.md) for running tests.

## Documentation

_Application Accelerators for VMware Tanzu_ documentation is provided as part of the official documentation for [VMware Tanzu Application Platform](https://techdocs.broadcom.com/us/en/vmware-tanzu/platform.html)

## Contributing

The application-accelerator-samples project team welcomes contributions from the community. Before you start working with application-accelerator-samples, please
read our [Developer Certificate of Origin](https://cla.vmware.com/dco). All contributions to this repository must be
signed as described on that page. Your signature certifies that you wrote the patch or have the right to pass it on
as an open-source patch. For more detailed information, refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## License

[MIT No Attribution](https://opensource.org/licenses/MIT-0) (MIT-0) License
