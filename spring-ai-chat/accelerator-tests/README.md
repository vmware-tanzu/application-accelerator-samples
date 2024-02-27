Accelerator Tests
===
This directory contains several shell scripts that act as tests for ensuring
that the project emitted by the accelerator is correct for a given set of
options. It also includes `testFunctions.sh`, a utility script used by the
other test scripts, and `runAllTests.sh`, a convenience script for running
all tests.

Prerequisites
---
The test scripts rely on the `tanzu` command line and assume that there is
an ACC Engine server running locally and listening on port 8877.

They also rely on the `grep` and `xpath` command line tools.

Running the tests
---
The easiest way to run the tests is to run them all at once:

```
% sh runAllTests.sh
```

This will run each of the test scripts in the order specified in the `runAllTests.sh` script.

You can also run any test individually. For example, to run the defaults
test:

```
% sh defaults.sh
```

The assertions made in the tests do not fail the test nor do they end
the test run. Instead, they simply print the problem to the console in
red. This is so that one failure doesn't prevent you from seeing other
failures and helps identify when the cause of a problem impacts several
assertions.

Note
---
The accelerator.yaml file is written to exclude accelerator-tests from
the resulting project. This is, in fact, asserted in the tests themselves.
