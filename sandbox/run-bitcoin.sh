#!/bin/bash

echo 'run bitcoin core'
DIR=/workdir
bitcoind -datadir=$DIR/bitcoin_dir --daemon
