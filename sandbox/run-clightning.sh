#!/bin/bash

echo 'run c-lightning'

DIR=/workdir

lightningd --lightning-dir=$DIR/lightning_dir_one --log-file=$DIR/lightning_dir_two/log.txt --daemon
lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon

lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo > node_one.info
address="$(lightning-cli --lightning-dir=$DIR/lightning_dir_one newaddr | jq -r '.bech32')"
echo "${address}"
# From https://bitcoincore.org/en/doc/0.21.0/rpc/wallet/sendtoaddress/
bitcoin-cli -datadir=$DIR/bitcoin_dir sendtoaddress "${address}" 50 "drinks" "room77" true true null "unset" null 1.1

lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo > node_two.info
address="$(lightning-cli --lightning-dir=$DIR/lightning_dir_two newaddr | jq -r '.bech32')"
echo "${address}"
# From https://bitcoincore.org/en/doc/0.21.0/rpc/wallet/sendtoaddress/
bitcoin-cli -datadir=$DIR/bitcoin_dir sendtoaddress "${address}" 50 "drinks" "room77" true true null "unset" null 1.1