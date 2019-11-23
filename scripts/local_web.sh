#!/usr/bin/env bash
#cleanup, compile and run locally

cd .. || exit
mvn clean package
cd codebase/web || exit
java -jar target/dependency/webapp-runner.jar target/*.war
cd - || exit
