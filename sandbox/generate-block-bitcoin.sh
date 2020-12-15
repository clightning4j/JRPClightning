#!/bin/bash

echo 'Sandbox Bitcoin core'
echo ''
echo 'author: https://github.com/vincenzopalazzo'
echo 'descriptions: This sanbox is builded for test https://github.com/vincenzopalazzo/JRPClightning'

DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC
VERSION=0.20.0
cd bitcoin-$VERSION/bin
address="$(./bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir getnewaddress)"
echo "Generate to ${address}"
./bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir generatetoaddress 1000 "${address}"

cd .. && cd ..
