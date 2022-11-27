package org.tlbc.hymns.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TaxonomyResultDTO {
    public TaxonomyResultDTO(String fromName, String toName, String type, String origin) {
        this.from_name = fromName;
        this.to_name = toName;
        this.type = type;
        this.origin = origin;
    }
    private String id;
    private String from_name;
    private String to_name;
    private String type;
    private String origin;
    private ResultValueDTO value;

}
