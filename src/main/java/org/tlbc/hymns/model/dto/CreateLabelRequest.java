package org.tlbc.hymns.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateLabelRequest {
    private Integer project;
    private String from_name;
    private String title;
    private String value;
}
