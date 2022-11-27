package org.tlbc.hymns.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateAnnotationDTO {
    private Integer id;
    private List<TaxonomyResultDTO> result;
    private String createdUsername;
    private Integer task;

}
