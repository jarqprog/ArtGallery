#!/usr/bin/env bash
#cleanup, compile and run locally

cd .. || exit
mvn clean package -Dspring.profiles.active=dev
cd codebase/api/api-art || exit
java -Dspring.profiles.active=dev -jar target/dependency/webapp-runner.jar target/*.war
cd - || exit
