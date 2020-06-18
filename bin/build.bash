#!/usr/bin/env bash

if [ ! -e m2 ]; then
  mkdir m2
fi


echo "Building employee manager image"

# Compile the code using maven docker container so users own env won't get polluted
docker run -it --rm --name emgmgr-build -v "$(pwd)":/usr/src/mymaven -v "$(pwd)/m2":/root/.m2 -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install

# Build the docker image
docker build -t rmg/empmgr:1.0 .
