package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.HymnEntity;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service @Slf4j
public class ConverterService {
    @Resource
    private ElasticSearchSongService elasticSearchSongService;
    @Resource
    private CategoryLabelService categoryLabelService;
    @Resource
    private HymnService hymnService;

    private Set<String> LABELS;

    public Integer updateOneHymn(Integer hymnId) {
        LABELS = categoryLabelService.getLabels();
        HymnEntity hymnEntity = hymnService.getHymnLabel(hymnId).orElseThrow();
        return updateLabelSet2ElasticSearchSongLabels(hymnEntity);
    }

    private Integer updateLabelSet2ElasticSearchSongLabels(HymnEntity hymnEntity) {
        ElasticSearchSong elasticSearchSong = elasticSearchSongService.getById(hymnEntity.getId()).orElseGet(() -> {
            log.debug("ready to add song, id:{}, name:{}", hymnEntity.getId(), hymnEntity.getNameCn());
            ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(getElasticSearchSong(hymnEntity));
            log.debug("saved new song: {} when updating hymn id:{}", savedElasticSearchSong, hymnEntity.getId());
            return savedElasticSearchSong;
        });
        boolean update = false;
        String matchedLabelSet = getMatchedLabelSet(hymnEntity.getLabelSet());
        log.debug("matchedLabelSet: {}, label in elastic search song:{}", matchedLabelSet, elasticSearchSong.getLabels());
        if (!matchedLabelSet.equals(elasticSearchSong.getLabels())) {
            elasticSearchSong.setLabels(matchedLabelSet);
            elasticSearchSong.setLabeled(true);
            update = true;
        }
        if (StringUtils.hasText(hymnEntity.getBookName()) && !hymnEntity.getBookName().equals(elasticSearchSong.getBookName())) {
            elasticSearchSong.setBookName(hymnEntity.getBookName());
            elasticSearchSong.setVerse(hymnEntity.getVerse());
            update = true;
        }
        if (update) {
            ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
            log.debug("savedSong: {}", savedElasticSearchSong);
            return 1;
        } else {
            log.debug("ignored one when updating: {}, {}", elasticSearchSong.getNameCn(), elasticSearchSong.getLabels());
            return 0;
        }
    }

    public Integer updateLabelSet2ElasticSearchSongLabels() {
        LABELS = categoryLabelService.getLabels();
        AtomicReference<Integer> count = new AtomicReference<>(0);
        hymnService.getHymnLabels().forEach((hymnLabel -> {
            Integer updateRow = updateLabelSet2ElasticSearchSongLabels(hymnLabel);
            if (updateRow > 0) {
                count.getAndSet(count.get() + 1);
            }
        }));
        return count.get();
    }

    private Integer addHymn2ElasticSearchSong(ElasticSearchSong elasticSearchSong) {
        ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
        log.debug("added song: {}", savedElasticSearchSong);
        return savedElasticSearchSong.getId();
    }

    public Integer hymn2ElasticSearchSong() {
        LABELS = categoryLabelService.getLabels();
        AtomicReference<Integer> count = new AtomicReference<>(0);
        hymnService.getHymnLabels().forEach((hymnLabel -> {
            Integer songId = addHymn2ElasticSearchSong(getElasticSearchSong(hymnLabel));
            log.debug("songId: {}", songId);
            count.getAndSet(count.get() + 1);
        }));
        return count.get();
    }

    private ElasticSearchSong getElasticSearchSong(HymnEntity hymnLabel) {
        ElasticSearchSong elasticSearchSong = new ElasticSearchSong(
                hymnLabel.getId(),
                hymnLabel.getCategory1(),
                hymnLabel.getCategory2(),
                hymnLabel.getNameCn(),
                hymnLabel.getNameEn());
        String labelSet = hymnLabel.getLabelSet();
        if (StringUtils.hasText(labelSet)) {
            String matchedLabelSet = getMatchedLabelSet(labelSet);
            if (StringUtils.hasText(matchedLabelSet)) {
                elasticSearchSong.setLabels(matchedLabelSet);
                elasticSearchSong.setLabeled(true);
            }
        }
        String bookName = hymnLabel.getBookName();
        if (!StringUtils.hasText(bookName)) {
            elasticSearchSong.setBookName(bookName);
            elasticSearchSong.setVerse(hymnLabel.getVerse());
        }
        return elasticSearchSong;
    }

    private String getMatchedLabelSet(String labelSet) {
        StringBuilder newLabelSet = new StringBuilder();
        Arrays.asList(labelSet.split("\\s+")).forEach((s -> {
            if (StringUtils.hasText(s)) {
                LABELS.forEach((label) -> {
                    if (label.contains(s) && !newLabelSet.toString().contains(label)) {
                        newLabelSet.append(label).append(" ");
                    }
                });
            }
        }));
        return newLabelSet.toString().trim();
    }
}
