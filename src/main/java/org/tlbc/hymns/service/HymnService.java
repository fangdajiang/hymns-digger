package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tlbc.hymns.model.AnnotatedHymnGroup;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.HymnGroup;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.repository.HymnRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class HymnService {
    private List<AnnotatedHymnGroup> groupList;
    private static final Integer LOAD_GROUP1_GROUP2_SAMPLE_COUNT = 5;

    @Resource
    private HymnRepository hymnRepository;
    @Resource
    private ElasticSearchSongService elasticSearchSongService;

    public List<HymnEntity> getHymns() {
        return hymnRepository.findAll(Sort.by(Sort.Direction.DESC, "group1"));
    }

    @PostConstruct
    public void loadGroups() {
        log.info("Loading groups from ElasticSearch...");
        this.groupList = getHymnGroups(true);
    }
    public List<AnnotatedHymnGroup> getLoadedHymnGroups() {
        return this.groupList;
    }
    public List<AnnotatedHymnGroup> getHymnGroups(boolean loadAll) {
        List<HymnGroup> hymnGroups = new ArrayList<>();
        List<AnnotatedHymnGroup> annotatedHymnGroups = new ArrayList<>();
        getHymns().forEach((h) -> {
            if (StringUtils.hasText(h.getGroup1()) && StringUtils.hasText(h.getGroup2())) {
                HymnGroup hg = new HymnGroup(h.getGroup1(), h.getGroup2());
                if (!hymnGroups.contains(hg)) {
                    List<ElasticSearchSong> elasticSearchSongs = new ArrayList<>();
                    if (loadAll || hymnGroups.size() <= LOAD_GROUP1_GROUP2_SAMPLE_COUNT) {
                        elasticSearchSongs = elasticSearchSongService.findByGroup2(h.getGroup2());
                    }
                    hymnGroups.add(hg);
                    annotatedHymnGroups.add(new AnnotatedHymnGroup(h.getGroup1(), h.getGroup2(), elasticSearchSongs.size()));
                    log.debug("hymn group: {}", hg);
                }
            } else {
                log.warn("group1 or group2 empty, g1:{}, g2:{}", h.getGroup1(), h.getGroup2());
            }
        });
        return annotatedHymnGroups;
    }

    public Set<String> getGroup1() {
        Set<String> set = new HashSet<>();
        getHymns().forEach((c) -> {
            if (StringUtils.hasText(c.getGroup1())) {
                set.add(c.getGroup1());
            }
        });
        return set;
    }

    public List<HymnEntity> getHymnLabels() {
        List<HymnEntity> hymnEntities = new ArrayList<>();
        getHymns().forEach((h) -> {
            if (StringUtils.hasText(h.getLabelSet())) {
                hymnEntities.add(h);
            }
        });
        return hymnEntities;
    }

    public Optional<HymnEntity> getHymnLabel(Integer hymnId) {
        return hymnRepository.findById(hymnId);
    }

}
