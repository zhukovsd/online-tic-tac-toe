#!/usr/bin/env bash

mkdir -p ./build/
# copy static html files
cp ./app/*.html ./build/



# build pug templates
./node_modules/.bin/pug app -o ./build -P
# build and bundle javascript
./node_modules/.bin/browserify app/app.js -t babelify -g browserify-css --debug -o build/app.js