#!/bin/bash

oc login -u kubeadmin -p db9Dr-J2csc-8oP78-9sbmf https://api.crc.testing:6443

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --list

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --delete --topic micro-integration-tracking-tool-event-topic

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --delete --topic micro-integration-tracking-tool-response-topic

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --delete --topic micro-tracking-tool-command-topic

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --delete --topic micro-vehicle-management-tool-event-topic

oc run kafka-tool -ti --image=registry.redhat.io/amq7/amq-streams-kafka-22 --rm=true --restart=Never -- bin/kafka-topics.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --delete --topic micro-vehicle-management-tool-response-topic
