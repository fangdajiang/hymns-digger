package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.service.LabelStudioService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController @Slf4j @Deprecated
public class LabelStudioActionController {
    @Resource
    private LabelStudioService labelStudioService;

    @GetMapping("/flush")
    public Integer flush() {
        return 0;
    }

    @GetMapping("/tasks")
    public List<LabelStudioAction> getTasks() {
        return null;
    }

    @PostMapping("/actions")
    public Map<String, Object> doElasticSearch(@RequestBody LabelStudioAction action) {
        log.debug("action: {}", action);
        return Map.of("song", labelStudioService.process(action));
    }

}
