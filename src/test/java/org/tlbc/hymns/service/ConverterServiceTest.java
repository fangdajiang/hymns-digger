package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.model.json.NewLabel;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    void getBasicLabel() {
        Set<String> basicLabels = converterService.getBasicLabel();
        log.debug("basicLabels size:{}, {}", basicLabels.size(), basicLabels);
    }

    @Test
    void getTaxonomyXml() {
        String s = converterService.getTaxonomyXml();
        log.debug("s: {}", s);
    }
    @Test
    void getTaxonomyBasicLabelXml() {
        String s = converterService.getTaxonomyBasicLabelXml();
        log.debug("s: {}", s);
    }
    @Test
    void getTaxonomyBasicLabelJson() {
        String s = converterService.getTaxonomyBasicLabelJson();
        log.debug("s: {}", s);
    }

    @Test
    void writeTaxonomyBasicLabelJson() {
        converterService.writeTaxonomyBasicLabelJson();
    }

    @Test
    void getTaxonomyNewLabels() {
        List<NewLabel> newLabels = converterService.getTaxonomyNewLabels();
        log.debug("newLabels: {}", newLabels);
    }

    @Test
    void getTaxonomyItemXml() {
        String s = converterService.getTaxonomyItemXml(List.of("神", "耶稣基督圣子"));
        log.debug("s: {}", s);
    }

    @Test
    void writeTaxonomyNewLabelJson() {
        converterService.writeTaxonomyNewLabelJson();
    }

    @Test
    void getCategoryLabels() {
        List<CategoryLabel> categoryLabels = converterService.getCategoryLabels();
        log.debug("categoryLabels size: {}", categoryLabels.size());
        List<List<CategoryLabel>> list = ConverterService.getPages(categoryLabels, 100);
        log.debug("list size: {}", list.size());
        log.debug("list 0 size: {}", list.get(0).size());
    }
}