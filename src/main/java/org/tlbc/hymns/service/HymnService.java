package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.HymnCategory;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.repository.HymnRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HymnService {
    @Resource
    private HymnRepository hymnRepository;

    public List<HymnEntity> getHymns() {
        return hymnRepository.findAll(Sort.by(Sort.Direction.DESC, "category1"));
    }

    public List<HymnCategory> getCategories() {
        List<HymnCategory> hymnCategories = new ArrayList<>();
        getHymns().forEach((h) -> hymnCategories.add(new HymnCategory(h.getId(), h.getCategory1(), h.getCategory2())));
        return hymnCategories;
    }

}
