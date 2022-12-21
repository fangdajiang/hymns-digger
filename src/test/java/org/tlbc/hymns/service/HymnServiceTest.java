package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.HymnGroup;
import org.tlbc.hymns.model.HymnEntity;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class HymnServiceTest {
    @Resource
    private HymnService hymnService;

    @Test
    void getHymnGroups() {
        List<HymnGroup> groups = hymnService.getHymnGroups();
        log.debug("groups size: {}, {}", groups.size(), groups);
        assertTrue(groups.size() > 0);
    }

    @Test
    void getHymnLabels() {
        List<HymnEntity> hymnEntities = hymnService.getHymnLabels();
        log.debug("non-empty labels size: {}, {}", hymnEntities.size(), hymnEntities);
        assertTrue(hymnEntities.size() > 0);
    }

    @Test
    void getHymns() {
        List<HymnEntity> hymnEntities = hymnService.getHymns();
        log.debug("hymn entities size: {}, {}", hymnEntities.size(), hymnEntities);
        assertTrue(hymnEntities.size() > 0);
    }
}