#!/bin/bash

echo 'run c-lightning'

DIR=/workdir

lightningd --lightning-dir=$DIR/lightning_dir_one --log-file=$DIR/lightning_dir_two/log.txt --daemon
lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon

lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo > node_one.info
address_one="$(lightning-cli --lightning-dir=$DIR/lightning_dir_one newaddr | jq -r '.bech32')"
echo "${address_one}"
# From https://bitcoincore.org/en/doc/0.21.0/rpc/wallet/sendtoaddress/
#bitcoin-cli -datadir=$DIR/bitcoin_dir sendtoaddress "${address}" 50 "drinks" "room77" true true null "unset" null 1.1
bitcoin-cli -datadir=$DIR/bitcoin_dir -named sendtoaddress address="${address_one}" amount=50 fee_rate=25 verbose=true

lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo > node_two.info
address_two="$(lightning-cli --lightning-dir=$DIR/lightning_dir_two newaddr | jq -r '.bech32')"
echo "${address_two}"
# From https://bitcoincore.org/en/doc/0.21.0/rpc/wallet/sendtoaddress/
#bitcoin-cli -datadir=$DIR/bitcoin_dir sendtoaddress "${address}" 50 "drinks" "room77" true true null "unset" null 1.1
bitcoin-cli -datadir=$DIR/bitcoin_dir -named sendtoaddress address="${address_two}" amount=50 fee_rate=25 verbose=true

./generate-block-bitcoin.sh

lightning-cli --lightning-dir=$DIR/lightning_dir_one listfunds
lightning-cli --lightning-dir=$DIR/lightning_dir_two listfunds