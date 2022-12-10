package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlbc.hymns.model.CategoryLabelEntity;
import org.tlbc.hymns.service.CategoryLabelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/labels")
public class CategoryLabelController {
    @Resource
    private CategoryLabelService categoryLabelService;

    @GetMapping
    public List<CategoryLabelEntity> getCategoryLabels() {
        return categoryLabelService.getCategoryLabels();
    }
    @GetMapping(value = "/categories")
    public Set<String> getCategories() {
        return categoryLabelService.getCategories();
    }
}
