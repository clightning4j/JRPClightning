echo 'Sandbox Bitcoin core'
echo ''
echo 'author: https://github.com/vincenzopalazzo'
echo 'descriptions: This sanbox is builded for test https://github.com/vincenzopalazzo/JRPClightning'

DIR=/workdir/sandbox

bitcoin-cli -datadir=$DIR/bitcoin_dir -regtest stop
cd .. && cd ..
