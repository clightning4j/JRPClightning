#!/bin/bash

echo 'generate bitcoin'
DIR=/workdir/sandbox
# -regtest -datadir=$DIR/bitcoin_di
address="$(bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir --rpcbind=172.60.0.1 getnewaddress)"
echo "Generate to ${address}"
bitcoin-cli -regtest -datadir=$DIR/bitcoin_dir generatetoaddress 1000 "${address}"

cd .. && cd ..
