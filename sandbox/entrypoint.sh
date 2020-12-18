#!/bin/bash
cd sandbox
./run-bitcoin.sh
./generate-block-bitcoin.sh
./run-clightning.sh
cd ..