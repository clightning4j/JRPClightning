#!/bin/bash

echo 'run c-lightning'

DIR=/workdir/sandbox

lightningd --lightning-dir=$DIR/lightning_dir_one --daemon
lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon
lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo
lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo
