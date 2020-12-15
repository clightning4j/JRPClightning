echo 'Sandbox Bitcoin core'
echo ''
echo 'author: https://github.com/vincenzopalazzo'
echo 'descriptions: This sanbox is builded for test https://github.com/vincenzopalazzo/JRPClightning'

DIR=/media/vincent/Maxtor/sanboxTestWrapperRPC

cd bitcoin-0.20.0/bin/
./bitcoin-cli -datadir=$DIR/bitcoin_dir -regtest stop
cd .. && cd ..
