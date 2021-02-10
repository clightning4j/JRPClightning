#!/bin/bash

echo 'run bitcoin core'
DIR=/workdir
bitcoind -regtest -datadir=$DIR/bitcoin_dir --daemon