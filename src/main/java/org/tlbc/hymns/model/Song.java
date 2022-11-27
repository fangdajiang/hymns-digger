package org.tlbc.hymns.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "song") @Data
public class Song {
    public Song(Integer id, String nameCn, String nameEn, String category1, String category2) {
        this.id = id;
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.category1 = category1;
        this.category2 = category2;
    }
    @Id
    private Integer id;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Text)
    private String nameCn;
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String labels;
    private String notationUrl;

    private String nameEn;
    private String category1;
    private String category2;

    private boolean labeled;
}
