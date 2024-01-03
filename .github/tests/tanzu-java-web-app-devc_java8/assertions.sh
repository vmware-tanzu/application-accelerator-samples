#!/bin/bash
set -euo pipefail

pushd $1
if [ ! -f .devcontainer.json ]; then
   echo "No .devcontainer.json file!"
   exit 1
else 
   if ! grep -q '"version": "8"' .devcontainer.json; then
      echo "Couldn't find Java version 8"
      exit 2
   fi
fi
popd