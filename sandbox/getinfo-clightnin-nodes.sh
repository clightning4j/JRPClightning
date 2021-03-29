#!/bin/bash

DIR=/workdir/sandbox

echo 'The info is'
lightning-cli --lightning-dir=$DIR/lightning_dir_one getinfo
echo 'the info was'
./lightning-cli --lightning-dir=$DIR/lightning_dir_two getinfo
