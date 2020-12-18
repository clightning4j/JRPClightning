#!/bin/bash
DIR=/workdir/sandbox

lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon

echo "lightning node runned"
