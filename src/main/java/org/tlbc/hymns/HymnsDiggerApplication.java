package org.tlbc.hymns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch-migration-guide-4.3-4.4.new-clients
@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class HymnsDiggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HymnsDiggerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
