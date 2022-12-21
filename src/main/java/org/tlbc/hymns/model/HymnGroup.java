package org.tlbc.hymns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString @AllArgsConstructor
public class HymnGroup {
    private Integer id;
    private String group1;
    private String group2;
}
