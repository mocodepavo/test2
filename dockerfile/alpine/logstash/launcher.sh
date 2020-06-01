#!/bin/sh

#- PROFILE como variable de entorno

logstash -f logback.conf &

java -jar app.jar 
