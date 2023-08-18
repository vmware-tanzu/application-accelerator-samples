#!/bin/bash
set -euo pipefail

pushd $1
if [ ! -f .devcontainer.json ]; then
   echo "No .devcontainer.json file!"
   exit 1
else 
   if ! grep -q '"version": "17"' .devcontainer.json; then
      echo "Couldn't find Java version 17"
      exit 2
   fi 
   if ! grep -q '"installMaven": true' .devcontainer.json; then
      echo "Couldn't find 'installMaven'"
      exit 3
   fi
   if grep -q 'installGradle' .devcontainer.json; then
      echo "devcontainer.json shouldn't have 'installGradle'"
      exit 4
   fi
fi
popd