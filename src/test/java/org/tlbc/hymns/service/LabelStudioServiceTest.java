package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.dto.CreateLabelDTO;

import javax.annotation.Resource;
import java.util.List;

@Slf4j @SpringBootTest
class LabelStudioServiceTest {

    @Resource
    private LabelStudioService service;

    @Test
    void process() {
    }

    @Test
    void createTasks() {
    }

    @Test
    void retrieveDataFromLabelStudio() {
        ElasticSearchSong elasticSearchSong = service.retrieveDataFromLabelStudio(3);
        log.debug("song retrieved from Label Studio: {}", elasticSearchSong);
    }

    @Test
    void deleteTask() {
    }

    @Test
    void createAnnotation() {
    }

    @Test
    void updateAnnotation() {
    }

    @Test
    void createLabel() {
        CreateLabelDTO createLabelDTO = service.createLabels(1, List.of("三一", "真神"));
        log.debug("createLabelDTO: {}", createLabelDTO);
    }

    @Test
    void fillInAnnotationsBackToLabelStudio() {
    }
}