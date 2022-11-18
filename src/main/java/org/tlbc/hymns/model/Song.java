package org.tlbc.hymns.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "song") @Data
public class Song {
    public Song(Integer id, String nameCn) {
        this.id = id;
        this.nameCn = nameCn;
    }
    @Id
    private Integer id;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Text)
    private String nameCn;
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private List<String> labels;
    private String notationUrl;

    private String nameEn;
    private String lyricists;
    private String composers;
    private int age;
    private String copyright;
}
