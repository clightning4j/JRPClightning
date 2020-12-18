#!/bin/bash

echo 'run bitcoin core'
DIR=/workdir/sandbox
bitcoind -regtest -datadir=$DIR/bitcoin_dir --daemon
cd .. && cd ..
