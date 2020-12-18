#!/bin/bash

echo 'Sandbox Bitcoin core'
echo ''
echo 'author: https://github.com/vincenzopalazzo'
echo 'descriptions: This sanbox is builded for test https://github.com/vincenzopalazzo/JRPClightning'

DIR=/workdir/sandbox
bitcoind -regtest -datadir=$DIR/bitcoin_dir --daemon
cd .. && cd ..
