# Tanzu Application Platform Initialize Accelerator

This is an accelerator to generate the necessary boilerplate for a TAP workload. Use this accelerator to generate the boilerplate files and then place them alongside the source code for your workload.

The following files are included:
- `config/workload.yaml`: The definition of a Workload for a TAP Supply Chain
- `catalog/catalog-info.yaml`: The definition of a Component to make the Workload available in the TAP GUI Catalog
- `Tiltfile`: A Tiltfile to enable Live Update and Debugging for Java Workloads using the TAP IDE Plugin

When generating new project files using this accelerator, set the `Name of target project` to match the name of the project that your are planning to add these files to.
