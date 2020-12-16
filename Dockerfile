FROM adoptopenjdk/openjdk13:jdk-13.0.2_8-ubuntu-slim
LABEL mantainer="Vincenzo Palazzo vincenzopalazzodev@gmail.com"

# Install Bitcoin and C-lightning
RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install -y wget && \
    apt-get install -y xz-utils

WORKDIR sandbox
COPY . .

RUN wget https://bitcoin.org/bin/bitcoin-core-0.20.0/bitcoin-0.20.0-x86_64-linux-gnu.tar.gz
RUN tar -xf bitcoin-0.20.0-x86_64-linux-gnu.tar.gz
RUN wget https://github.com/ElementsProject/lightning/releases/download/v0.9.2/clightning-v0.9.2-Ubuntu-18.04.tar.xz
RUN tar -xf clightning-v0.9.2-Ubuntu-18.04.tar.xz
RUN mv usr clightning

ENV PATH "$PATH:/sandbox/bitcoin-0.20.0/bin"
ENV PATH "$PATH:/sandbox/clightning/bin"

CMD ["./gradlew", "tests"]