package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.helper.PaginationHelper;
import org.tlbc.hymns.model.CategoryLabel;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class LabelServiceTest {

    @Resource
    private LabelService labelService;

    @Test
    void getCategoryLabels() {
        List<CategoryLabel> categoryLabels = labelService.getCategoryLabels();
        log.debug("categoryLabels size: {}", categoryLabels.size());
        assertTrue(categoryLabels.size() > 0);
        List<List<CategoryLabel>> list = PaginationHelper.getPages(categoryLabels, 200);
        log.debug("list size: {}", list.size());
        assertTrue(list.size() > 0);
        log.debug("list 0 size: {}", list.get(0).size());
        assertTrue(list.get(0).size() > 0);
    }

    @Test
    void getBasicLabel() {
        Set<String> basicLabels = labelService.getBasicLabel();
        log.debug("basicLabels size:{}, {}", basicLabels.size(), basicLabels);
        assertTrue(basicLabels.size() > 0);
    }
}