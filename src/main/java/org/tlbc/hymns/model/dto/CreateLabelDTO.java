package org.tlbc.hymns.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data @NoArgsConstructor
public class CreateLabelDTO implements Serializable {
    private Integer id;
    private Integer created_by;
    private Integer organization;
    private Integer project;
    private String from_name;
    private String title;
    private String value;
    private Boolean approved;
    private Integer approved_by;
    private List<Integer> projects;
}
