#!/bin/bash
set -euo pipefail

cd $(dirname ${BASH_SOURCE[0]})

java_pid=$(cat engine_pid)
echo "Stopping process $java_pid"
kill $java_pid
