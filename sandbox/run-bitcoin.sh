#!/bin/bash

echo 'Sandbox Bitcoin core'
echo ''
echo 'author: https://github.com/vincenzopalazzo'
echo 'descriptions: This sanbox is builded for test https://github.com/vincenzopalazzo/JRPClightning'

DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC
cd bitcoin-0.20.0/bin
./bitcoind -datadir=$DIR/bitcoin_dir --daemon -regtest
#./bitcoin-cli -datadir=/media/vincenzo/Maxtor/sanboxTestWrapperRPC/bitcoin_dir generate 6
cd .. && cd ..
