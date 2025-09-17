#!/usr/bin/env bash

set -e -x

cd spring_boot_app

# copy build dir into container first to mitigate access rights conflicts
docker run \
  -u gradle \
  -v "$PWD":/home/gradle/project \
  --name build-spring-boot-container \
  gradle:jdk21-noble \
  bash -c "cp -r /home/gradle/project /tmp/ && cd /tmp/project && gradle jibBuildTar"

docker cp build-spring-boot-container:/tmp/project/build/jib-image.tar .
docker rm build-spring-boot-container
docker load --input jib-image.tar
