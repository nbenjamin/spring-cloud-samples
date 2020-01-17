# base image
FROM alpine:3.11.2

MAINTAINER nbenjamin

# set vault version
ENV VAULT_VERSION 1.3.1

# release package name
ENV VAULT_PACKAGE vault_${VAULT_VERSION}_linux_amd64.zip

# create a vault directory
RUN mkdir /vault

# download ca-certificates
RUN apk --no-cache add ca-certificates

# download wget
RUN apk --no-cache add wget

# download bash
RUN apk --no-cache add bash

RUN echo https://releases.hashicorp.com/vault/${VAULT_VERSION}/${VAULT_PACKAGE}
# download and set up vault
# -q, --quiet   quiet (no output).
# -O,  --output-document=FILE    write documents to FILE.
RUN wget -q --output-document=/tmp/vault.zip https://releases.hashicorp.com/vault/${VAULT_VERSION}/${VAULT_PACKAGE}

# -d  extract files into exdir
RUN unzip /tmp/vault.zip -d /vault

# remove tmp directory
RUN rm -f /tmp/vault.zip

# make it executable
RUN chmod +x /vault

# update PATH
ENV PATH="PATH=$PATH:$PWD/vault"

# add the vault config json file, this can be either json or hcl
COPY ./config/vault-config.json /vault/config/vault-config.json

# expose port 8200
EXPOSE 8200

# run vault
ENTRYPOINT ["vault"]