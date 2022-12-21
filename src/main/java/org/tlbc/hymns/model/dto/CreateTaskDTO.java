package org.tlbc.hymns.model.dto;

import lombok.Data;

@Data
public class CreateTaskDTO {
    private Integer id;
    private Integer project;
    private Boolean is_labeled;
    private Integer total_annotations;
    private String annotations_ids;

    private SongDTO data;

    @Data
    public static class SongDTO {
        private String text;
        private String nameEn;
        private String group1;
        private String group2;
        private String labels;
    }
}
