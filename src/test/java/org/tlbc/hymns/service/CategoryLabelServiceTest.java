package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.helper.PaginationHelper;
import org.tlbc.hymns.model.CategoryLabelEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j @SpringBootTest
class CategoryLabelServiceTest {

    @Resource
    private CategoryLabelService categoryLabelService;

    @Test
    void getTaxonomy() {
        Map<String, List<CategoryLabelEntity>> map = categoryLabelService.getTaxonomy();
        log.debug("taxonomy size: {}", map.size());
        map.forEach((k, v) -> {
            List<String> labels = v.stream().map(CategoryLabelEntity::getLabel).collect(Collectors.toList());
            log.debug("k:{}, v.label:{}", k, labels);
        });
    }

    @Test
    void getCategoryLabels() {
        List<CategoryLabelEntity> categoryLabelEntities = categoryLabelService.getCategoryLabels();
        log.debug("categoryLabels size: {}", categoryLabelEntities.size());
        assertTrue(categoryLabelEntities.size() > 0);
        List<List<CategoryLabelEntity>> list = PaginationHelper.getPages(categoryLabelEntities, 200);
        log.debug("list size: {}", list.size());
        assertTrue(list.size() > 0);
        log.debug("list 0 size: {}", list.get(0).size());
        assertTrue(list.get(0).size() > 0);
    }

    @Test
    void getCategories() {
        Set<String> categories = categoryLabelService.getCategories();
        log.debug("categories size:{}, {}", categories.size(), categories);
        assertTrue(categories.size() > 0);
    }

    @Test
    void getLabels() {
        Set<String> labels = categoryLabelService.getLabels();
        log.debug("labels size:{}, {}", labels.size(), labels);
        assertTrue(labels.size() > 0);
    }

    @Test
    void test() {
        log.debug("set to string: {}", categoryLabelService.getLabels().toString());
        log.debug("1: {}", categoryLabelService.getLabels().toString().contains("主餐"));
    }
}