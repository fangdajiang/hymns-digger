package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ConverterServiceTest {
    @Resource
    private ConverterService converterService;

    @Test
    void hymn2ElasticSearchSong() {
        Integer count = converterService.hymn2ElasticSearchSong();
        log.debug("count: {}", count);
        assertTrue(count > 0);
    }

    @Test
    void updateLabelSet2ElasticSearchSongLabels() {
        Integer count = converterService.updateLabelSet2ElasticSearchSongLabels();
        log.debug("count: {}", count);
    }

    @Test
    void updateOneHymn() {
        Integer count = converterService.updateOneHymn(280);
        log.debug("count: {}", count);
    }
}