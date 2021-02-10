#!/bin/bash

echo 'generate bitcoin'
DIR=/workdir
address="$(bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir getnewaddress)"
echo "Generate to ${address}"
bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir generatetoaddress 1000 "${address}"