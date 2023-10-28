FROM opensearchproject/opensearch:1.2.4

COPY --chown=opensearch:opensearch opensearch.yml /usr/share/opensearch/config/

RUN /usr/share/opensearch/bin/opensearch-plugin remove opensearch-security