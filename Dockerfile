FROM ubuntu:18.04
LABEL mantainer="Vincenzo Palazzo vincenzopalazzodev@gmail.com"

# Install Bitcoin and C-lightning
RUN apt-get update -y && \
	apt-get install -y software-properties-common && \
	add-apt-repository ppa:bitcoin/bitcoin && \
	add-apt-repository -u ppa:lightningnetwork/ppa && \
	apt-get update -y && \
	apt-get install -y bitcoind && \
	apt-get install -y lightningd && \
	apt-get remove --purge -y software-properties-common && \
	apt-get autoremove -y && \
	apt-get clean

CMD bitcoind -printtoconsole


