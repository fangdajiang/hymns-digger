package org.tlbc.hymns.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.CategoryLabel;
import org.tlbc.hymns.model.json.BasicLabel;
import org.tlbc.hymns.model.xml.*;
import org.tlbc.hymns.repository.CategoryLabelRepository;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
@Service @Slf4j
public class ConverterService {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @Resource
    private CategoryLabelRepository categoryLabelRepository;

    public Map<String, List<CategoryLabel>> getTaxonomy() {
        List<CategoryLabel> list = categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category"));
        return list.stream().collect(Collectors.groupingBy(CategoryLabel::getCategory));
    }

    public String getTaxonomyXml() {
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
            s = XML_MAPPER.writeValueAsString(view);
        } catch (JsonProcessingException e) {
            log.error("writeValueAsString ERROR", e);
        }
        return s;
    }

    public String getTaxonomyBasicLabelJson() {
        JsonMapper jsonMapper = new JsonMapper();
        BasicLabel basicLabel = new BasicLabel(getTaxonomyBasicLabelXml());
        String s = "";
        try {
            s = jsonMapper.writeValueAsString(basicLabel);
        } catch (IOException e) {
            log.error("getTaxonomyBasicLabelJson ERROR", e);
        }
        return s;
    }

    public void writeTaxonomyBasicLabelJson() {
        try {
            FileUtil.writeString(getTaxonomyBasicLabelJson(), new File("src/main/resources/json/basic_labels.json"), CharsetUtil.CHARSET_UTF_8);
        } catch (IORuntimeException e) {
            log.error("writeTaxonomyBasicLabelJson ERROR", e);
        }
    }

    public Set<String> getBasicLabel() {
        Set<String> set = new HashSet<>();
        categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category")).forEach((c) -> set.add(c.getCategory()));
        return set;
    }
    public String getTaxonomyBasicLabelXml() {
        String s = "";
        try {
            View view = new View();
            view.setText(new Text("text", "$text"));
            List<Choice> choices = new ArrayList<>();
            getBasicLabel().forEach((c) -> choices.add(new Choice(c, List.of())));
            Taxonomy taxonomy = new Taxonomy("taxonomy", "text", choices);
            view.setTaxonomy(taxonomy);
            s = XML_MAPPER.writeValueAsString(view);
        } catch (JsonProcessingException e) {
            log.error("writeValueAsString ERROR", e);
        }
        return s;
    }
}
