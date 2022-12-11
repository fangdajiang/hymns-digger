package org.tlbc.hymns.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryLabel {
    public CategoryLabel(Integer id, String c, String l) {
        this.id = id;
        this.category = c;
        this.label = l;
    }

    private Integer id;
    private String category;
    private String label;
    private Integer labelAnnotatedCount;
}
