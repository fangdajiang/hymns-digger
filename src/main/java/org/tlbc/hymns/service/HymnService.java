package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tlbc.hymns.model.HymnCategory;
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
        return hymnRepository.findAll(Sort.by(Sort.Direction.DESC, "category1"));
    }

    public List<HymnCategory> getHymnCategories() {
        List<HymnCategory> hymnCategories = new ArrayList<>();
        getHymns().forEach((h) -> hymnCategories.add(new HymnCategory(h.getId(), h.getCategory1(), h.getCategory2())));
        return hymnCategories;
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
