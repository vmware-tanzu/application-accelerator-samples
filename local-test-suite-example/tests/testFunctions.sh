#!/bin/bash

# Assert that the file exists
assertFileExists()
{
  if ! [ -f $1 ]; then
    echo "$1 DOES NOT exist"
    exit 1
  fi
}

# Assert that a file does NOT exist
assertFileDoesntExist()
{
  if [ -f $1 ]; then
    echo "$1 SHOULD NOT exist"
    exit 1
  fi
}

# Assert that a directory does NOT exist
assertDirDoesntExist()
{
  if [ -d $1 ]; then
    echo "$1 SHOULD NOT exist"
    exit 1
  fi
}

# Assert that a file contains some string
assertFileContains()
{
  if ! grep -Fq "$2" $1; then
    echo "$1 DOES NOT contain \"$2\""
    exit 1
  fi
}

# Assert that a file DOES NOT contain some string
assertFileDoesntContain()
{
  if grep -Fq "$2" $1; then
    echo "$1 SHOULD NOT contain \"$2\""
    exit 1
  fi
}

# Assert project's groupId and artifactId
assertPomHasProjectCoordinates()
{
  DEPTEST="//project[groupId=\"$2\"][artifactId=\"$3\"]"
  DEPRESULT=`xpath -e $DEPTEST -q $1 2>/dev/null`
  if [ ${#DEPRESULT} -eq "0" ]; then
    echo "$1 does not have project coordinates \"$2:$3\""
    exit 1
  fi
}

# Assert that the pom has a given dependency
assertPomHasDependency()
{
  DEPTEST="//project/dependencies/dependency[groupId=\"$2\"][artifactId=\"$3\"]"
  if [ $# -gt 3 ]; then
    DEPTEST="$DEPTEST[version=\"$4\"]"
  fi

  DEPRESULT=`xpath -e $DEPTEST -q $1 2>/dev/null`
  if [ ${#DEPRESULT} -eq "0" ]; then
    echo "$1 does not contain dependency \"$2:$3:$4\""
    exit 1
  fi
}

# Assert that the pom DOES NOT have a given dependency
assertPomDoesntHaveDependency()
{
  DEPTEST="//project/dependencies/dependency[groupId=\"$2\"][artifactId=\"$3\"]"
  if [ $# -gt 3 ]; then
    DEPTEST="$DEPTEST[version=\"$4\"]"
  fi

  DEPRESULT=`xpath -e $DEPTEST -q $1 2>/dev/null`
  if ! [ ${#DEPRESULT} -eq "0" ]; then
    echo "$1 does not contain dependency \"$2:$3:$4\""
    exit 1
  fi
}
