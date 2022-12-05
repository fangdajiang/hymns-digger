package org.tlbc.hymns.model.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class View {
    @JsonProperty("Text")
    private Text text;
    @JsonProperty("Taxonomy")
    private Taxonomy taxonomy;
}
