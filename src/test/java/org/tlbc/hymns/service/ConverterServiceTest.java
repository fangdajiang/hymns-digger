package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.CategoryLabel;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j @SpringBootTest
class ConverterServiceTest {

    @Resource
    private ConverterService converterService;

    @Test
    void getTaxonomy() {
        Map<String, List<CategoryLabel>> map = converterService.getTaxonomy();
        log.debug("taxonomy size: {}", map.size());
        map.forEach((k, v) -> {
            List<String> labels = v.stream().map(CategoryLabel::getLabel).collect(Collectors.toList());
            log.debug("k:{}, v.label:{}", k, labels);
        });
    }

    @Test
    void getTaxonomyString() {
        String s = converterService.getTaxonomyString();
        log.debug("s: {}", s);
    }
}