package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tlbc.hymns.model.HymnGroup;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.repository.HymnRepository;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class HymnService {
    @Resource
    private HymnRepository hymnRepository;

    public List<HymnEntity> getHymns() {
        return hymnRepository.findAll(Sort.by(Sort.Direction.DESC, "group1"));
    }

    public List<HymnGroup> getHymnGroups() {
        List<HymnGroup> hymnGroups = new ArrayList<>();
        getHymns().forEach((h) -> {
            if (StringUtils.hasText(h.getGroup1()) && StringUtils.hasText(h.getGroup2())) {
                HymnGroup hg = new HymnGroup(h.getGroup1(), h.getGroup2());
                if (!hymnGroups.contains(hg)) {
                    hymnGroups.add(hg);
                }
            }
        });
        return hymnGroups;
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
