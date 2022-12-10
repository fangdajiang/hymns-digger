package org.tlbc.hymns.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString @AllArgsConstructor
@Deprecated
public class NewLabel {
    public NewLabel() {
        this.createdBy = 1;
        this.organization = 1;
        this.project = 1;
        this.fromName = "taxonomy";
        this.approved = true;
    }

    @JsonProperty("created_by")
    private Integer createdBy;
    @JsonProperty("organization")
    private Integer organization;
    @JsonProperty("project")
    private Integer project;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("value")
    private List<String> value;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("approved")
    private Boolean approved;
    @JsonProperty("approved_by")
    private String approvedBy;
}
