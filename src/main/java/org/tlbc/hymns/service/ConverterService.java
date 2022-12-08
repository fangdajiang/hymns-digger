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
import org.tlbc.hymns.helper.PaginationHelper;
import org.tlbc.hymns.model.CategoryLabelEntity;
import org.tlbc.hymns.model.json.BasicLabel;
import org.tlbc.hymns.model.json.NewLabel;
import org.tlbc.hymns.model.json.TaxonomyLabelPair;
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
    private static final JsonMapper JSON_MAPPER = new JsonMapper();
    private static final String BASIC_LABELS_PATH = "src/main/resources/json/basic_labels.json";
    private static final String NEW_LABELS_PATH_PREFIX = "src/main/resources/json/new_labels";
    private static final String NEW_LABELS_PATH_SUFFIX = ".json";

    @Resource
    private CategoryLabelRepository categoryLabelRepository;

    public Map<String, List<CategoryLabelEntity>> getTaxonomy() {
        return getCategoryLabels().stream().collect(Collectors.groupingBy(CategoryLabelEntity::getCategory));
    }

    public List<CategoryLabelEntity> getCategoryLabels() {
        return categoryLabelRepository.findAll(Sort.by(Sort.Direction.DESC, "category"));
    }

    public String getTaxonomyXml() {
        String s = "";
        try {
            View view = new View();
            view.setText(new Text("text", "$text"));
            List<Choice> choices = new ArrayList<>();
            getTaxonomy().forEach((k, v) -> {
                List<String> labels = v.stream().map(CategoryLabelEntity::getLabel).collect(Collectors.toList());
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
        BasicLabel basicLabel = new BasicLabel(getTaxonomyBasicLabelXml());
        String s = "";
        try {
            s = JSON_MAPPER.writeValueAsString(basicLabel);
        } catch (IOException e) {
            log.error("getTaxonomyBasicLabelJson ERROR", e);
        }
        return s;
    }

    public void writeTaxonomyBasicLabelJson() {
        try {
            FileUtil.writeString(getTaxonomyBasicLabelJson(), new File(BASIC_LABELS_PATH), CharsetUtil.CHARSET_UTF_8);
        } catch (IORuntimeException e) {
            log.error("writeTaxonomyBasicLabelJson ERROR", e);
        }
    }

    public Set<String> getBasicLabel() {
        Set<String> set = new HashSet<>();
        getCategoryLabels().forEach((c) -> set.add(c.getCategory()));
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
    public String getTaxonomyItemXml(List<String> labelPair) {
        String s = "";
        try {
            TaxonomyLabelPair taxonomyLabelPair = new TaxonomyLabelPair(labelPair);
            s = JSON_MAPPER.writeValueAsString(taxonomyLabelPair);
        } catch (JsonProcessingException e) {
            log.error("writeValueAsString ERROR", e);
        }
        return s;
    }
    public List<NewLabel> getTaxonomyNewLabels() {
        List<NewLabel> newLabels = new ArrayList<>();
        getCategoryLabels().forEach((cl) -> {
            NewLabel newLabel = new NewLabel();
            newLabel.setValue(List.of(cl.getCategory(), cl.getLabel()));
            newLabel.setTitle(getTaxonomyItemXml(List.of(cl.getCategory(), cl.getLabel())));
            log.debug("newLabel:{}", newLabel);
            newLabels.add(newLabel);
        });
        return newLabels;
    }

    public void writeTaxonomyNewLabelJson() {
        List<NewLabel> newLabels = getTaxonomyNewLabels();
        Integer pageSize = 200;
        List<List<NewLabel>> list = PaginationHelper.getPages(newLabels, pageSize);
        for (int i = 0; i < list.size(); i++) {
            try {
                FileUtil.writeString(JSON_MAPPER.writeValueAsString(list.get(i)),
                        new File(NEW_LABELS_PATH_PREFIX + i + NEW_LABELS_PATH_SUFFIX),
                        CharsetUtil.CHARSET_UTF_8
                );
            } catch (IOException e) {
                log.error("writeValueAsString ERROR", e);
            } catch (IORuntimeException e) {
                log.error("writeTaxonomyNewLabelJson ERROR", e);
            }
        }
    }

}
