#!/usr/bin/env bash
java -jar -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar firstApp.jar --server.port=8080