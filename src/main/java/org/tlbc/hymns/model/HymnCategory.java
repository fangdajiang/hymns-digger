package org.tlbc.hymns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString @AllArgsConstructor
public class HymnCategory {
    private Integer id;
    private String category1;
    private String category2;
}
