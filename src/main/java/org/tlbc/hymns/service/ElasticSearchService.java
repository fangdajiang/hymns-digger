package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.model.Song;

import javax.annotation.Resource;
import java.util.Map;

@Service @Slf4j
public class ElasticSearchService {
    private static String ES_HYMNS_DIGGER_SEARCH_URL = "http://localhost:9200/hymns-picker-tasks-default/_search";

    private enum ACTION {
        ANNOTATION_UPDATED, TASKS_DELETED, TASKS_CREATED
    }

    @Resource
    public RestTemplate restTemplate;

    public Song process(LabelStudioAction action) {
        if (ACTION.TASKS_CREATED.name().equals(action.getAction())) {
            addTask(action);
        } else if (ACTION.ANNOTATION_UPDATED.name().equals(action.getAction())) {
            updateAnnotation(action);
        } else if (ACTION.TASKS_DELETED.name().equals(action.getAction())) {
            removeTask(action);
        } else {
            log.error("Illegal action: {}", action.getAction());
        }
        return new Song("中文");
    }

    public void removeTask(LabelStudioAction action) {
    }

    public void updateAnnotation(LabelStudioAction action) {

    }

    public void addTask(LabelStudioAction action) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> tokenResponseEntity = restTemplate.exchange(ES_HYMNS_DIGGER_SEARCH_URL,
                HttpMethod.GET, new HttpEntity<>(headers), String.class, Map.of());
        log.debug("tokenResponseEntity:{}", tokenResponseEntity);
    }
}
