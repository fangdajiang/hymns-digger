package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tlbc.hymns.model.HymnGroup;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.repository.HymnRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        getHymns().forEach((h) -> hymnGroups.add(new HymnGroup(h.getId(), h.getGroup1(), h.getGroup2())));
        return hymnGroups;
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
