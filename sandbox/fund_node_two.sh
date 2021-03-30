#!/bin/bash
DIR=/workdir

# lightning-cli --lightning-dir=$DIR/lightning_dir_two listfunds

for run in {1..2}; do
  address_two="$(lightning-cli --lightning-dir=$DIR/lightning_dir_two newaddr | jq -r '.bech32')"
  #echo "${address_two}"
  # From https://bitcoincore.org/en/doc/0.21.0/rpc/wallet/sendtoaddress/
  #bitcoin-cli -datadir=$DIR/bitcoin_dir sendtoaddress "${address}" 50 "drinks" "room77" true true null "unset" null 1.1
  bitcoin-cli -datadir=$DIR/bitcoin_dir -named sendtoaddress address="${address_two}" amount=1 fee_rate=1 > /dev/null
  address="$(bitcoin-cli -datadir=$DIR/bitcoin_dir getnewaddress)"
  bitcoin-cli -datadir=$DIR/bitcoin_dir generatetoaddress 10 "${address}" > /dev/null
done
