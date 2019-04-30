#!/usr/bin/env bash

mvn clean install -T 6 -DskipTests
mvn install -Ppackage-docker-image -T 6 -DskipTests