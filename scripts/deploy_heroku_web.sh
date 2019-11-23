#!/usr/bin/env bash

cd .. || exit
mvn clean package
cd codebase/web || exit
mvn heroku:deploy-only
cd - || exit
