package org.tlbc.hymns.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "hymn", schema = "digger")
public class HymnEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "category1")
    private String category1;
    @Column(name = "category2")
    private String category2;
    @Column(name = "label_set")
    private String labelSet;
    @Column(name = "name_cn")
    private String nameCn;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "verse")
    private String verse;
}
