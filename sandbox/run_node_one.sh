#!/bin/bash
DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC
VERSION=v0.9.0rc3
cd clightning-$VERSION/bin
./lightningd --lightning-dir=$DIR/lightning_dir_one --daemon