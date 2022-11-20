package org.tlbc.hymns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class SongSummary {
    private Integer totalNumber;
    private Integer annotationCount;
}
