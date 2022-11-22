#!/bin/bash
set -euxo pipefail
pushd $1

# Check that http files exist
test -f func.py
grep -q 'main(req: Any)' func.py

# Check that TAP files exist
test -f config/workload.yaml
test -f catalog/catalog-info.yaml

popd