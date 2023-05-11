#!/bin/bash
docker pull dev.registry.tanzu.vmware.com/app-accelerator/acc-engine:latest
docker run -d -p 8888:8080 dev.registry.tanzu.vmware.com/app-accelerator/acc-engine:latest
