package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class ElasticSearchServiceTest {

    @Resource
    private ElasticSearchService service;

    @Test
    void removeTask() {
    }

    @Test
    void updateAnnotation() {
    }

    @Test
    void addTask() {
        log.debug("okok:{}", service.restTemplate);
//        service.restTemplate.postForObject()
    }
}