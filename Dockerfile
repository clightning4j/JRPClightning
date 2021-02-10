FROM ubuntu:20.04
LABEL mantainer="Vincenzo Palazzo vincenzopalazzodev@gmail.com"

# Ubuntu utils
RUN apt-get update && apt-get install -y \
   software-properties-common

## Install jdk 11
RUN add-apt-repository -y ppa:linuxuprising/java
RUN apt-get update  && apt-get install openjdk-11-jdk -y

# Install bitcoin core and lightningd (last version)
RUN add-apt-repository ppa:luke-jr/bitcoincore
RUN apt-get update  && apt-get install -y bitcoind
RUN add-apt-repository -u ppa:lightningnetwork/ppa
RUN apt-get update  && apt-get install -y lightningd

WORKDIR workdir
COPY sandbox .

RUN chmod +x *.sh
RUN ls -l

CMD ["./entrypoint.sh"]