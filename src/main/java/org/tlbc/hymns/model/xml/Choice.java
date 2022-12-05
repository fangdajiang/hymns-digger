package org.tlbc.hymns.model.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter @AllArgsConstructor
public class Choice {
    @JacksonXmlProperty(isAttribute = true)
    private String value;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Choice")
    private List<ChoiceSub> choiceSubs;
}
