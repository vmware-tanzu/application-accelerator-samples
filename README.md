# application-accelerator-samples

## Overview

Application Accelerator for VMware Tanzu helps you bootstrap developing your applications and deploying them in a discoverable and repeatable way.

Enterprise Architects author and publish accelerator projects that provide developers and operators in their organization ready-made, enterprise-conformant code and configurations.

Published accelerators projects are maintained in Git repositories. You can then use Application Accelerator to create new projects based on those accelerator projects.

The Application Accelerator user interface(UI) enables you to discover available accelerators, configure them, and generate new projects to download.

## How does Application Accelerator work?

Application Accelerator allows you to generate new projects from files in Git repositories. An accelerator.yaml file in the repository declares input options for the accelerator. This file also contains instructions for processing the files when you generate a new project.

Accelerator custom resources (CRs) control which repositories appear in the Application Accelerator UI. You can maintain CRs by using Kubernetes tools such as kubectl or by using the Tanzu CLI accelerator commands. The Accelerator controller reconciles the CRs with a Flux2 Source Controller to fetch files from GitHub or GitLab.

The Application Accelerator UI and IDE extension gives you a searchable list of accelerators to choose from. After you select an accelerator, the UI and IDE extension presents input fields for any input options.

Application Accelerator sends the input values to the Accelerator Engine for processing. The Engine then returns the project in a ZIP file. You can open the project in your favorite integrated development environment (IDE) to develop further.

### Prerequisites

To use these Application Accelerator samples you need to have access to an installation of [VMware Tanzu Application Platform](https://network.tanzu.vmware.com/products/tanzu-application-platform).

### Build & Run

Each Application Accelerator is provided in a separate directory and is its own self contained project with a README file descibing how to build it.

## Documentation

_Application Accelerators for VMware Tanzu_ documentation is provided as part of the official documentation for [VMware Tanzu Application Platform](https://docs.vmware.com/en/VMware-Tanzu-Application-Platform/index.html)

## Contributing

The application-accelerator-samples-for-vmware-tanzu project team welcomes contributions from the community. Before you start working with application-accelerator-samples-for-vmware-tanzu, please
read our [Developer Certificate of Origin](https://cla.vmware.com/dco). All contributions to this repository must be
signed as described on that page. Your signature certifies that you wrote the patch or have the right to pass it on
as an open-source patch. For more detailed information, refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## License

[MIT No Attribution](https://opensource.org/licenses/MIT-0) (MIT-0) License
