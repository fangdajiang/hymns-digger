package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.service.LabelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/labels")
public class LabelController {
    @Resource
    private LabelService labelService;

    @GetMapping
    public List<CategoryLabel> getCategoryLabels() {
        return labelService.getCategoryLabels();
    }
    @GetMapping(value = "/categories")
    public Set<String> getCategories() {
        return labelService.getBasicLabel();
    }
}
