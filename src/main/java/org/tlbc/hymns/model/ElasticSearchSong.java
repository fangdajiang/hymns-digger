package org.tlbc.hymns.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "song") @Data
public class ElasticSearchSong {
    public ElasticSearchSong(Integer id, String group1, String group2, String nameCn, String nameEn) {
        this.id = id;
        this.group1 = group1;
        this.group2 = group2;
        this.nameCn = nameCn;
        this.nameEn = nameEn;
    }
    @Id
    private Integer id;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String nameCn;
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String labels;
    private String notationUrl;

    private String nameEn;
    private String group1;
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String group2;
    private String bookName;
    private String verse;

    private boolean labeled;
}
