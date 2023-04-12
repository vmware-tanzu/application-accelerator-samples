#!/bin/bash
set -euxo pipefail
pushd $1

# Check that backendService name is used
grep -q 'customer-profile-backend.namespace' nginx.conf

npm install
npm install -g @angular/cli
ng build
ng test --browsers=ChromeHeadless --watch=false

popd
