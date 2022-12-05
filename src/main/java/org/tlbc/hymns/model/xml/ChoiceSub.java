package org.tlbc.hymns.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ChoiceSub {
    public ChoiceSub(String v) {
        this.value = v;
    }
    @JacksonXmlProperty(isAttribute = true)
    private String value;
}
