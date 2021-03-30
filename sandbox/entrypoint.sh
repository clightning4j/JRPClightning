#!/bin/bash
./run-bitcoin.sh
./generate-block-bitcoin.sh
./run-clightning.sh
cd code
./gradlew test