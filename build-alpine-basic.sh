#!/bin/bash

oc login -u kubeadmin -p db9Dr-J2csc-8oP78-9sbmf https://api.crc.testing:6443

mvn clean install -DskipTests -PPROD

rm ./dockerfile/alpine/basic/app.jar

cp ./target/app.jar ./dockerfile/alpine/basic

docker rmi default-route-openshift-image-registry.apps-crc.testing/track-vehicle-data/micro-vehicle-management-tool

docker build -t default-route-openshift-image-registry.apps-crc.testing/track-vehicle-data/micro-vehicle-management-tool -f dockerfile/alpine/basic/Dockerfile .

docker login -u kubeadmin -p $(oc whoami -t) default-route-openshift-image-registry.apps-crc.testing

docker push default-route-openshift-image-registry.apps-crc.testing/track-vehicle-data/micro-vehicle-management-tool
