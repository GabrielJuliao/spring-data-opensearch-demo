package com.example.springdataopensearch.configuration;

import com.example.springdataopensearch.repository.ProductRepository;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.OpenSearchRestTemplate;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@AutoConfiguration
@EnableElasticsearchRepositories(basePackageClasses = ProductRepository.class)
public class OpenSearchConfiguration extends AbstractOpenSearchConfiguration {

    boolean useSsl;

    @Override
    public RestHighLevelClient opensearchClient() {
        return RestClients.create(clientConfiguration()).rest();
    }

    @Bean
    public OpenSearchRestTemplate openSearchRestTemplate() {
        return new OpenSearchRestTemplate(this.opensearchClient());
    }

    public ClientConfiguration clientConfiguration() {

        ClientConfiguration.MaybeSecureClientConfigurationBuilder maybeSecureClientConfigurationBuilder = ClientConfiguration.builder()
                .connectedTo("localhost:9200");

        if (useSsl) {
            maybeSecureClientConfigurationBuilder.usingSsl(buildSslCOntext());
        }

        maybeSecureClientConfigurationBuilder.withClientConfigurer(webClient -> {
            ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                    .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(-1))
                    .build();
            return WebClient.builder().exchangeStrategies(exchangeStrategies).build();
        });

        return maybeSecureClientConfigurationBuilder.build();
    }

    public SSLContext buildSslCOntext() {
        try {
            return new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
