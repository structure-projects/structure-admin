#!/bin/bash
version=$1
if [ -z "$version" ]; then
    version=1.0.2
fi
echo 'maven-build'
sh install.sh
mvn clean package -f ../structure-admin-boot/pom.xml -Dmaven.test.skip=true -Drevision=$version
mvn clean package -f ../structure-admin-cloud/pom.xml -Dmaven.test.skip=true -Drevision=$version