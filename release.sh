#!/usr/bin/env bash

if [[ $# -ne 1 ]]
then
  echo "$0 <version>"
  exit 1
fi

mvn versions:set -DnewVersion="$1"
mvn versions:commit

git checkout origin/master
git pull origin master
git tag "$1"
git push orgin master --