#!/bin/bash

echo ''
echo 'Run procedure to run two node lightning'
echo ''

DIR=/workdir/sandbox

lightning-cli --lightning-dir=$DIR/lightning_dir_one stop
echo 'stop first node'

echo 'stop node two'
lightning-cli --lightning-dir=$DIR/lightning_dir_two stop
