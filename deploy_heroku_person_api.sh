#!/usr/bin/env bash

mvn clean package
cd codebase/api/api-person || exit
mvn heroku:deploy-only
cd - || exit
