package org.tlbc.hymns.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.model.xml.*;
import org.tlbc.hymns.repository.CategoryLabelRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service @Slf4j
public class ConverterService {

    @Resource
    private CategoryLabelRepository categoryLabelRepository;

    public Map<String, List<CategoryLabel>> getTaxonomy() {
        List<CategoryLabel> list = categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category"));
        return list.stream().collect(Collectors.groupingBy(CategoryLabel::getCategory));
    }

    public String getTaxonomyString() {
        XmlMapper xmlMapper = new XmlMapper();
        String s = "";
        try {
            View view = new View();
            view.setText(new Text("text", "$text"));
            List<Choice> choices = new ArrayList<>();
            getTaxonomy().forEach((k, v) -> {
                List<String> labels = v.stream().map(CategoryLabel::getLabel).collect(Collectors.toList());
                log.debug("k:{}, v.label:{}", k, labels);
                List<ChoiceSub> choiceSubs = labels.stream().map(ChoiceSub::new).collect(Collectors.toList());
                log.debug("choiceSubs:{}", choiceSubs);
                choices.add(new Choice(k, choiceSubs));
            });
            Taxonomy taxonomy = new Taxonomy("taxonomy", "text", choices);
            view.setTaxonomy(taxonomy);
            s = xmlMapper.writeValueAsString(view);
        } catch (JsonProcessingException e) {
            log.error("writeValueAsString ERROR", e);
        }
        return s;
    }
}
