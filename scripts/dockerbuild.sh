#!/bin/bash
version=$1
if [ -z "$version" ]; then
    version=1.0.1
fi
echo 'docker-build'
cd ../
docker build -t registry.cn-hangzhou.aliyuncs.com/structured/structured-admin:$version .
