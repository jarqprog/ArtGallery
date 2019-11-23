#!/usr/bin/env bash

cd .. || exit
mvn clean package
cd codebase/api/api-person || exit
mvn heroku:deploy-only
cd - || exit
