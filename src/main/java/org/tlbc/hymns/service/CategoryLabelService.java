package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.model.CategoryLabelEntity;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.repository.CategoryLabelRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
@Service @Slf4j
public class CategoryLabelService {

    private List<CategoryLabel> categoryLabelList;

    private static final Integer LOAD_CATEGORY_LABEL_SAMPLE_COUNT = 5;

    @Resource
    private CategoryLabelRepository categoryLabelRepository;
    @Resource
    private ElasticSearchSongService elasticSearchSongService;

    public Map<String, List<CategoryLabelEntity>> getTaxonomy() {
        return getCategoryLabelEntities().stream().collect(Collectors.groupingBy(CategoryLabelEntity::getCategory));
    }

    public List<CategoryLabel> getLoadedCategoryLabels() {
        return this.categoryLabelList;
    }
    @PostConstruct
    public void loadCategoryLabels() {
        log.info("Loading category labels from ElasticSearch...");
        this.categoryLabelList = getCategoryLabels(true);
    }

    public List<CategoryLabel> getCategoryLabels(boolean loadAll) {
        List<CategoryLabel> categoryLabels = new ArrayList<>();
        getCategoryLabelEntities().forEach(categoryLabelEntity -> {
            CategoryLabel categoryLabel = new CategoryLabel(categoryLabelEntity.getId(), categoryLabelEntity.getCategory(), categoryLabelEntity.getLabel());
            List<ElasticSearchSong> elasticSearchSongs = new ArrayList<>();
            if (loadAll || categoryLabels.size() <= LOAD_CATEGORY_LABEL_SAMPLE_COUNT) {
                elasticSearchSongs = elasticSearchSongService.findByLabels(Set.of(categoryLabelEntity.getLabel()));
            }
            log.debug("category:{}, label:{}, elasticSearchSongs size: {}", categoryLabel.getCategory(), categoryLabel.getLabel(), elasticSearchSongs.size());
            categoryLabel.setLabelAnnotatedCount(elasticSearchSongs.size());
            categoryLabels.add(categoryLabel);
        });
        return categoryLabels;
    }

    public List<CategoryLabelEntity> getCategoryLabelEntities() {
        return categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category"));
    }

    public Set<String> getCategories() {
        Set<String> set = new HashSet<>();
        getCategoryLabelEntities().forEach((c) -> set.add(c.getCategory()));
        return set;
    }
    public Set<String> getLabels() {
        Set<String> set = new HashSet<>();
        getCategoryLabelEntities().forEach((c) -> set.add(c.getLabel()));
        return set;
    }

}