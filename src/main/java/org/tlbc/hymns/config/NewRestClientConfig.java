package org.tlbc.hymns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class NewRestClientConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.hostAndPort}")
    private String hostAndPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .build();
    }
}
