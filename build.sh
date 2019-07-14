#!/usr/bin/env bash

export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

mvn clean install -P Impl -T 1C -DdependencyLocationsEnabled=false