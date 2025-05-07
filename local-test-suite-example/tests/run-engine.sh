#!/bin/bash
set -euo pipefail

export ACC_LOCAL_ACCELERATOR_DEFS=$PWD/accelerator-resources.yaml
export PATH=$PWD/../app/bin:$PATH
export SERVER_PORT=8378
export LSP_SERVER_PORT=-1
java -cp $PWD/../app/jars/acc-engine.jar com.vmware.tanzu.accelerator.local.server.MainKt --spring.main.banner-mode=off
