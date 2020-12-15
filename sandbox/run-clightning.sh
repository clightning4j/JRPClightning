#!/bin/bash

DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC
VERSION=v0.9.2

cd clightning-$VERSION/bin
./lightningd --lightning-dir=$DIR/lightning_dir_one --daemon
./lightningd --lightning-dir=$DIR/lightning_dir_two --log-file=$DIR/lightning_dir_two/log.txt --daemon
./lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo
./lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo
