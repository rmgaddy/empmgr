#!/usr/bin/env bash


echo "Running employee manager container"
docker run -p 9101:8080/tcp --rm --name empmgr rmg/empmgr:1.0
