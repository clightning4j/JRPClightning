#!/bin/bash
DIR=/workdir
bitcoin-cli -datadir=$DIR/bitcoin_dir -rpcwait createwallet "java"
address="$(bitcoin-cli -datadir=$DIR/bitcoin_dir getnewaddress)"
bitcoin-cli -datadir=$DIR/bitcoin_dir generatetoaddress 1000 "${address}"