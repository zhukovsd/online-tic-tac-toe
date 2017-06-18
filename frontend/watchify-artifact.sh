#!/usr/bin/env bash

# set current directory to ./frontend explicitly, because this script gets executed from a root project dir
cd "${0%/*}"

watchify app/app.js -t babelify -g browserify-css --debug -o ./../out/artifacts/online_tic_tac_toe_war_exploded/app.js -v