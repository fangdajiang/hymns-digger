package org.tlbc.hymns.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class LabelStudioAction {
    private String action;
    private LabelStudioAnnotation annotation;
    private LabelStudioProject project;
    private LabelStudioTask task;
    private List<LabelStudioTask> tasks;

    @Getter @Setter @ToString
    public static class LabelStudioAnnotation {
        private Integer id;
        private List<AnnotationResult> result;
        private Date created_at;
        private Date updated_at;
        private Integer result_count;
        private Integer task;
        private Integer completed_by;

        @Getter @Setter @ToString
        public static class AnnotationResult {
            private ResultValue value;
            private String id;
            private String from_name;
            private String to_name;
            private String type;
            private String origin;

            @Getter @Setter @ToString
            public static class ResultValue {
                private List<List<String>> taxonomy;
            }
        }
    }

    @Getter @Setter @ToString
    private static class LabelStudioProject {
        private Integer id;
        private Integer task_number;
        private String title;
        private String description;
        private String label_config;
        private Date created_at;
        private Date updated_at;
        private Integer organization;
        private Integer created_by;
    }

    @Getter @Setter @ToString
    public static class LabelStudioTask {
        private Integer id;
        private TaskData data;
        private Date created_at;
        private Date updated_at;
        private Boolean is_labeled;

        @Getter @Setter @ToString
        public static class TaskData {
            private String text;
        }
    }
}
