#!/usr/bin/env bash

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

mvn versions:set -DnewVersion="$1"
mvn versions:commit