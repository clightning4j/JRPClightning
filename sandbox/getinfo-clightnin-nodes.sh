#!/bin/bash

DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC
VERSION=v0.9.2

cd clightning-$VERSION/bin
echo 'The info is'
./lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo
echo 'the info was'
./lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo
