#!/bin/bash
set -euxo pipefail
pushd $1

# Check that cloudevents files exist
test -f func.py
grep -q 'main(data: Any, attributes: dict)' func.py

# Check that TAP files exist
test -f config/workload.yaml
test -f catalog/catalog-info.yaml

popd