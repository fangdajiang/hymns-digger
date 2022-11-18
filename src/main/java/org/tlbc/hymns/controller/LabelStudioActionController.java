package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.service.ElasticSearchService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController @Slf4j
public class LabelStudioActionController {
    @Resource
    private ElasticSearchService elasticSearchService;

    @GetMapping("/tasks")
    public List<LabelStudioAction> getTasks() {
        return null;
    }

    @PostMapping("/actions")
    public Map<String, Object> updateElasticSearch(@RequestBody LabelStudioAction action) {
        log.debug("action: {}", action);
        return Map.of("song", elasticSearchService.update(action));
    }

    @PostMapping("/actions2")
    public void printRequestBody(@RequestBody Map<String, Object> map) {
        log.debug("map: {}", map);
    }
}
