#!/bin/bash
DIR=/workdir
bitcoind -datadir=$DIR/bitcoin_dir -server -logtimestamps -regtest -nolisten --daemon
