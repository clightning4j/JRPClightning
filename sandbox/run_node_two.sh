#!/bin/bash
DIR=/workdir

lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon

echo "lightning node runned"
