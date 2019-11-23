#!/usr/bin/env bash

cd .. || exit
mvn clean package
cd codebase/api/api-art || exit
mvn heroku:deploy-only
cd - || exit
