#!/bin/bash

docker run -d --rm -p 1521:1521 -p 81:81 -v /Users/sarcosh/Documents/TEMP:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2