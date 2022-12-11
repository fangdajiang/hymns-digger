package org.tlbc.hymns.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.dto.*;
import org.tlbc.hymns.util.HymnUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service @Slf4j @Deprecated
public class LabelStudioService {

    public static final String BASE_CHAR_NUMBER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Resource
    private ElasticSearchSongService elasticSearchSongService;

    private enum ACTION {
        ANNOTATION_CREATED, ANNOTATION_UPDATED, TASKS_CREATED, TASKS_DELETED, LABEL_LINK_CREATED
    }

    public ElasticSearchSong process(LabelStudioAction action) {
        if (ACTION.TASKS_CREATED.name().equals(action.getAction())) {
            createTasks(action);
        } else if (ACTION.TASKS_DELETED.name().equals(action.getAction())) {
            deleteTask(action);
        } else if (ACTION.ANNOTATION_CREATED.name().equals(action.getAction())) {
            createAnnotation(action);
        } else if (ACTION.ANNOTATION_UPDATED.name().equals(action.getAction())) {
            updateAnnotation(action);
        } else if (ACTION.LABEL_LINK_CREATED.name().equals(action.getAction())) {
            // ignore
            log.info("ignore LABEL_LINK_CREATED");
        } else {
            log.error("Illegal action: {}", action.getAction());
        }
        return new ElasticSearchSong(123, "中文", "Eng", "分类1", "分类2");
    }
    public void createTasks(LabelStudioAction action) {
        for (LabelStudioAction.LabelStudioTask task : action.getTasks()) {
            ElasticSearchSong elasticSearchSong = retrieveDataFromLabelStudio(task.getId());
            if (ObjectUtil.isNotNull(elasticSearchSong)) {
                ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
                log.debug("savedSong: {}", savedElasticSearchSong);
            } else {
                log.warn("retrieve data from label studio failed, task id:{}, action:{}", task.getId(), action);
            }
        }
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws Exception {
        if (map == null) {
            return null;
        }
        T t = clazz.getDeclaredConstructor().newInstance();
        BeanUtils.populate(t, map);
        return t;
    }

    public CreateLabelDTO createLabels(Integer projectId, List<String> labels) {
        List<CreateLabelRequest> createLabelRequestList = new ArrayList<>();
        for (String label : labels) {
            createLabelRequestList.add(new CreateLabelRequest(projectId, "taxonomy", "去读", label));
        }
        String createLabelRequestJson = JSON.toJSONString(createLabelRequestList);
        log.debug("createLabelRequest json list: {}", createLabelRequestJson);
        List<?> createLabelDTOList = HymnUtil.sendHttpRequest("http://localhost:8080/api/labels", HttpMethod.POST,
                createLabelRequestJson, List.class, null).getBody();
        assert createLabelDTOList != null;
        log.debug("createLabelDTO response map: {}", createLabelDTOList.get(0));
        CreateLabelDTO createLabelDTO = null;
        try {
            createLabelDTO = mapToObject((Map)createLabelDTOList.get(0), CreateLabelDTO.class);
            log.debug("createLabelDTO: {}", createLabelDTO);
        } catch (Exception e) {
            log.warn("map:{}", createLabelDTOList.get(0), e);
        }
        return createLabelDTO;
    }

    public Integer fillInAnnotationsBackToLabelStudio(CreateTaskDTO createTaskDTO) {
        if (StrUtil.isEmpty(createTaskDTO.getAnnotations_ids())) {
            log.debug("ready to fill in annotations[{}] back for task: {}", createTaskDTO.getData().getLabels(), createTaskDTO.getId());

            List<String> labels = Arrays.asList(createTaskDTO.getData().getLabels().split("\\s+"));
            log.debug("labels: {}", labels);
            List<List<String>> taxonomy = new ArrayList<>();
            for (String label : labels) {
                List<String> l = new ArrayList<>();
                l.add("神");
                l.add(label);
                taxonomy.add(l);
            }
            CreateLabelDTO createLabelDTO = createLabels(createTaskDTO.getProject(), labels);
            log.debug("labels created: {}", createLabelDTO);

            ResultValueDTO resultValueDTO = new ResultValueDTO();
            resultValueDTO.setTaxonomy(taxonomy);
            TaxonomyResultDTO taxonomyResultDTO = new TaxonomyResultDTO("taxonomy", "text", "taxonomy", "digger");
            taxonomyResultDTO.setId(RandomUtil.randomString(BASE_CHAR_NUMBER, 10));
            taxonomyResultDTO.setValue(resultValueDTO);

            CreateAnnotationRequest createAnnotationRequest = new CreateAnnotationRequest();
            createAnnotationRequest.setTask(createTaskDTO.getId());
            createAnnotationRequest.setResult(List.of(taxonomyResultDTO));

            log.debug("createAnnotationRequest: {}", createAnnotationRequest);
            String createAnnotationDTOString = HymnUtil.sendHttpRequest("http://localhost:8080/api/tasks/{1}/annotations/",
                    HttpMethod.POST, JSON.toJSONString(createAnnotationRequest), String.class, createTaskDTO.getId().toString()).getBody();
            assert createAnnotationDTOString != null;
            log.debug("createAnnotationDTO response string: {}", createAnnotationDTOString);
            CreateAnnotationDTO createAnnotationDTO = JSONUtil.toBean(createAnnotationDTOString, CreateAnnotationDTO.class);
            log.debug("createAnnotationDTO: {}", createAnnotationDTO);
            return createAnnotationDTO.getId();
        } else {
            return 0;
        }
    }

    public ElasticSearchSong retrieveDataFromLabelStudio(Integer taskId) {
        CreateTaskDTO createTaskDTO = HymnUtil.sendHttpRequest("http://localhost:8080/api/tasks/{1}", HttpMethod.GET,
                null, CreateTaskDTO.class, taskId.toString()).getBody();
        log.debug("createTaskDTO: {}", createTaskDTO);
        assert createTaskDTO != null;
        Integer annotationId = fillInAnnotationsBackToLabelStudio(createTaskDTO);
        if (annotationId > 0) {
            ElasticSearchSong elasticSearchSong = new ElasticSearchSong(taskId, createTaskDTO.getData().getText(),
                    createTaskDTO.getData().getNameEn(),
                    createTaskDTO.getData().getCategory1(),
                    createTaskDTO.getData().getCategory2());
            String labelsString = createTaskDTO.getData().getLabels();
            if (!StrUtil.isBlank(labelsString)) {
                elasticSearchSong.setLabels(labelsString);
                elasticSearchSong.setLabeled(true);
            }
            return elasticSearchSong;
        } else {
            return null;
        }
    }
    public void deleteTask(LabelStudioAction action) {
        for (LabelStudioAction.LabelStudioTask task : action.getTasks()) {
            elasticSearchSongService.delete(task.getId());
            log.debug("song deleted, id: {}", task.getId());
        }
    }

    public void createAnnotation(LabelStudioAction action) {
        ElasticSearchSong elasticSearchSong = elasticSearchSongService.getById(action.getTask().getId()).orElseGet(() -> {
            log.debug("ready to add song, id:{}, name:{}", action.getTask().getId(), action.getTask().getData().getText());
            ElasticSearchSong newElasticSearchSong = new ElasticSearchSong(action.getTask().getId(), action.getTask().getData().getText(), "", "", "");
            ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(newElasticSearchSong);
            log.debug("savedSong: {} when creating annotation", savedElasticSearchSong);
            return savedElasticSearchSong;
        });
        elasticSearchSong.setLabels(getNewLabels(action));
        elasticSearchSong.setLabeled(action.getTask().getIs_labeled());
        ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
        log.debug("savedSong: {} when creating annotation", savedElasticSearchSong);
    }
    public void updateAnnotation(LabelStudioAction action) {
        ElasticSearchSong elasticSearchSong = elasticSearchSongService.getById(action.getTask().getId()).orElseThrow();
        elasticSearchSong.setLabels(getNewLabels(action));
        elasticSearchSong.setLabeled(action.getTask().getIs_labeled());
        ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
        log.debug("savedSong: {} when updating annotation", savedElasticSearchSong);
    }

    private static String getNewLabels(LabelStudioAction action) {
        List<LabelStudioAction.LabelStudioAnnotation.AnnotationResult> results = action.getAnnotation().getResult();
        if (results.size() > 0) {
            StringBuilder labels = new StringBuilder();
            List<List<String>> taxonomy = results.get(0).getValue().getTaxonomy();
            for (List<String> labelCombination : taxonomy) {
                labels.append(labelCombination.get(1)).append(" ");
            }
            return labels.toString();
        } else {
            return "";
        }
    }
}
