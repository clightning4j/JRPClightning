#!/bin/bash

echo 'run c-lightning'

DIR=/workdir

lightningd --lightning-dir=$DIR/lightning_dir_one --log-file=$DIR/lightning_dir_two/log.txt --daemon
lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon
lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo
lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo
