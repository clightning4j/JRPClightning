FROM adoptopenjdk/openjdk13:jdk-13.0.2_8-ubuntu-slim
LABEL mantainer="Vincenzo Palazzo vincenzopalazzodev@gmail.com"

# Install Bitcoin and C-lightning
RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install -y wget && \
    apt-get install -y xz-utils && \
    apt-get install -y libpq-dev && \
    apt-get install -y libsodium-dev

WORKDIR workdir
COPY . .

RUN wget https://bitcoin.org/bin/bitcoin-core-0.20.0/bitcoin-0.20.0-x86_64-linux-gnu.tar.gz
RUN tar -xf bitcoin-0.20.0-x86_64-linux-gnu.tar.gz
RUN wget https://github.com/ElementsProject/lightning/releases/download/v0.9.2/clightning-v0.9.2-Ubuntu-18.04.tar.xz
RUN tar -xf clightning-v0.9.2-Ubuntu-18.04.tar.xz
RUN mv usr clightning-v0.9.2

ENV PATH "$PATH:/workdir/bitcoin-0.20.0/bin"
ENV PATH "$PATH:/workdir/clightning-v0.9.2/bin"

RUN ["chmod", "+x", "/workdir/sandbox/entrypoint.sh"]
RUN ["chmod", "+x", "/workdir/sandbox/run-bitcoin.sh"]
RUN ["chmod", "+x", "/workdir/sandbox/generate-block-bitcoin.sh"]
RUN ["chmod", "+x", "/workdir/sandbox/run-clightning.sh"]

CMD ["/workdir/sandbox/entrypoint.sh"]