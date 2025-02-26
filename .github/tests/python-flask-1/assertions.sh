#!/bin/bash
set -euxo pipefail
pushd $1

# Check that TP files exist
test -f .tanzu/config/python-flask.yml
test -f tanzu.yml

# Check that app files exist
test -f Procfile
test -f requirements.txt
test -f web.py

popd