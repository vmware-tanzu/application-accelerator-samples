#!/bin/bash
set -euxo pipefail
pushd $1

# Check that app files exist
test -f Procfile
test -f requirements.txt
test -f web.py

popd