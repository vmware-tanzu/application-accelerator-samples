#!/bin/bash
tanzu acc delete kdv
tanzu acc create kdv2 \
  --git-repo https://github.com/kdvolder/application-accelerator-samples.git \
  --git-branch tanzu-java-web-app-gradle \
  --git-sub-path tanzu-java-web-app \
