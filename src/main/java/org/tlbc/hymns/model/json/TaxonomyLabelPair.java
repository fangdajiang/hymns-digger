package org.tlbc.hymns.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class TaxonomyLabelPair {
    @JsonProperty("taxonomy")
    private List<String> labelPair;
}
