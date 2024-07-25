#!/bin/bash
version=$1
if [ -z "$version" ]; then
    version=1.0.1
fi
echo 'maven-build'
cd ../structure-admin-boot
mvn clean package -Dmaven.test.skip=true -Drevision=$version
cd ../structure-admin-cloud
mvn clean package -Dmaven.test.skip=true -Drevision=$version