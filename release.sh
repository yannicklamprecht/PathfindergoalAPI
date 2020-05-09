#!/usr/bin/env bash

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

git checkout origin/master
git pull origin master
mvn versions:set -DnewVersion="$1"
mvn versions:commit
git tag "$1"
git push origin master --tags