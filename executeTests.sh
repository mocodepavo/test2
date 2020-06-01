#!/bin/bash

hey -n 1 -c 1 -m POST -T "application/json" -D "test/create_commercial_order.json" "http://openshift4.ddns.net:8080/vehicle_management_tool/api/v1/order"