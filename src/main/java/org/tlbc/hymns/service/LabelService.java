package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.repository.CategoryLabelRepository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LabelService {
    @Resource
    private CategoryLabelRepository categoryLabelRepository;

    public List<CategoryLabel> getCategoryLabels() {
        return categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category"));
    }
    public Set<String> getBasicLabel() {
        Set<String> set = new HashSet<>();
        getCategoryLabels().forEach((c) -> set.add(c.getCategory()));
        return set;
    }

}
