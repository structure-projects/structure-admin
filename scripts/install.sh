#!/usr/bin/env bash
#在本地仓库安装.RELEASE
version=$1
if [ -z "$version" ]; then
    version=1.0.2
fi
cd ../
mvn clean install -Dmaven.test.skip=true -Drevision=$version
