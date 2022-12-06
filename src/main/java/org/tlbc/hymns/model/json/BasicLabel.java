package org.tlbc.hymns.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class BasicLabel {
    @JsonProperty("label_config")
    private String LabelConfig;
}
