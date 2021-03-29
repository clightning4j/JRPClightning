#!/bin/bash
DIR=/workdir
bitcoind -datadir=$DIR/bitcoin_dir -server -regtest -nolisten --daemon
ps aux | grep bitcoind
bitcoin-cli -datadir=$DIR/bitcoin_dir -rpcwait createwallet "java"
