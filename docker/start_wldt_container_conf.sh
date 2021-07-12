#!/bin/bash

docker run --name=wl-digital-twin \
    -v $(pwd)/logback.xml:/usr/local/src/mvnapp/src/main/resources/logback.xml \
    -v $(pwd)/logback.xml:/usr/local/src/mvnapp/target/classes/logback.xml \
    -v $(pwd)/wldt.yaml:/usr/local/src/mvnapp/wldt.yaml \
    -v $(pwd)/mqtt.yaml:/usr/local/src/mvnapp/mqtt.yaml \
    -d orch-wldt:0.2
