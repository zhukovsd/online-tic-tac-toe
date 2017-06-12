#!/usr/bin/env bash

# set current directory to ./frontend explicitly, because this script gets executed from a root project dir
cd "${0%/*}"

./node_modules/.bin/pug app -o ./../out/artifacts/online_tic_tac_toe_war_exploded/ -P --watch