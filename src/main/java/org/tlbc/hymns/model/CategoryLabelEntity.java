package org.tlbc.hymns.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "category_label", schema = "digger")
public class CategoryLabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "label", nullable = false)
    private String label;
}
