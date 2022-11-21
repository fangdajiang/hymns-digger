package org.tlbc.hymns.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration @Slf4j
public class NewRestClientConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.hostAndPort}")
    private String hostAndPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        log.info("hostAndPort:{}", hostAndPort);
        return ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .build();
    }
}
